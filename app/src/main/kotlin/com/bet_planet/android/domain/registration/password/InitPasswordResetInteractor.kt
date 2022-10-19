package com.bet_planet.android.domain.registration.password

import android.annotation.SuppressLint
import android.content.Context
import com.bet_planet.android.R
import com.bet_planet.android.domain.main.MainAction
import com.bet_planet.android.domain.main.NavigateAction
import com.bet_planet.android.domain.main.handlerApiException
import com.bet_planet.android.domain.navigation.Screen
import com.bet_planet.android.domain.registration.reset_password.ResetPasswordArguments
import com.bet_planet.android.presentation.signInFlow.reset_password.VerifySmsCodeMode
import com.bet_planet.components.domain.Interactor
import com.bet_planet.components.domain.Schedulers
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.ofType
import javax.inject.Inject

private const val SHOW_DIALOG_SERVER_CODE = 103
private const val WRONG_PHONE_SERVER_CODE = 100

private val errorCodes = setOf(
        SHOW_DIALOG_SERVER_CODE,
        WRONG_PHONE_SERVER_CODE
)

class InitPasswordResetInteractor @Inject constructor(
        private val passwordSource: PasswordSource,
        private val schedulers: Schedulers,
        private val context: Context,
        private val mainActions: Relay<MainAction>
) : Interactor<PasswordAction, PasswordState> {

    @SuppressLint("SimpleDateFormat")
    override fun bind(
            actions: Observable<PasswordAction>,
            state: Observable<PasswordState>
    ): Observable<PasswordAction> {
        return actions.ofType<InitPasswordResetAction>()
                .observeOn(schedulers.io())
                .flatMapSingle { action ->
                    passwordSource.initPasswordReset("${action.country.code}${action.phone}")
                            .map { response -> response to nullThrowable() }
                            .handleApiException()
                            .flatMap { response ->
                                Single.just(response)
                            }.flatMap {
                                if (it.code == 10) {
                                    passwordSource
                                            .getResetCode("${action.country.code}${action.phone}")
                                } else {
                                    Single.just(it)
                                }
                            }
                            .map {
                                if (it.code == 118 && !it.userId.isNullOrEmpty()) {
                                    mainActions.accept(
                                            NavigateAction(
                                                    Screen.RESET_PASSWORD,
                                                    data = ResetPasswordArguments(
                                                            action.phone,
                                                            action.country,
                                                            checkType = VerifySmsCodeMode.SMS_RECOVERY_PASSWORD,
                                                            userId = it.userId
                                                    ))
                                    )
                                } else {
                                    ErrorOnPasswordScreenAction(context.getString(R.string.something_gone_wrong))
                                }
                            }
                }
                .map {
                    if (it is PasswordAction) {
                        it
                    } else {
                        FinishRequestAction
                    }
                }

    }

    private fun nullThrowable() = null as Throwable?

    private fun <T> Single<Pair<T, Throwable?>>.handleApiException(): Single<T> {
        return flatMap { (response, throwable) ->
            if (throwable != null) {
                Single.error<T>(throwable)
                        .handlerApiException(mainActions) {
                            it.serverCode !in errorCodes
                        }
                        .onErrorReturn { response }
            } else {
                Single.just(response)
            }
        }
    }
}
