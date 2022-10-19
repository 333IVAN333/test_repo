package com.bet_planet.android.domain.registration.reset_password

import android.annotation.SuppressLint
import android.content.Context
import com.bet_planet.android.R
import com.bet_planet.android.data.utils.analitycs.AnalyticsUtil
import com.bet_planet.android.domain.main.MainAction
import com.bet_planet.android.domain.main.NavigateAction
import com.bet_planet.android.domain.main.handlerApiException
import com.bet_planet.android.domain.navigation.Screen
import com.bet_planet.android.domain.registration.login.LoginSource
import com.bet_planet.android.domain.registration.password.PasswordSource
import com.bet_planet.android.domain.user.auth.UserAuthSource
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

class ResetPasswordInteractor @Inject constructor(
        private val passwordSource: PasswordSource,
        private val loginSource: LoginSource,
        private val schedulers: Schedulers,
        private val userAuthSource: UserAuthSource,
        private val analyticsUtil: AnalyticsUtil,
        private val context: Context,
        private val mainActions: Relay<MainAction>
) : Interactor<ResetPasswordAction, ResetPasswordState> {

    @SuppressLint("SimpleDateFormat")
    override fun bind(
            actions: Observable<ResetPasswordAction>,
            state: Observable<ResetPasswordState>
    ): Observable<ResetPasswordAction> {
        return actions.ofType<SendResetPasswordAction>()
                .observeOn(schedulers.io())
                .flatMapSingle { action ->
                    passwordSource.resetPassword(
                            action.userID,
                            action.smsCode,
                            action.newPass)
                            .flatMap {
                                if (it.code == 10) {
                                    loginSource.login("${action.countryCode}${action.phone}", action.newPass)
                                } else {
                                    throw ResetException(context.getString(R.string.wrong_code))
                                }
                            }
                            .map { response -> response to nullThrowable() }
                            .handleApiException()
                            .flatMap { response ->
                                Single.just(response)
                            }.map {
                                analyticsUtil.logSuccessLogin()
                                if (it.isSuccessLogin()) {
                                    userAuthSource.apply {
                                        phoneCountryCode = action.countryCode
                                        userId = it.userId!!
                                        phoneNumber = action.phone
                                        password = action.newPass
                                    }
                                    userAuthSource.lastUsedTime = 0
                                    mainActions.accept(
                                            NavigateAction(Screen.QUESTION_PIN_CODE, null)
                                    )
                                    FinishRequestAction
                                } else {
                                    analyticsUtil.logErrorLogin(it.errorCode)
                                    ErrorRequestAction(context.getString(R.string.something_gone_wrong))
                                }
                            }
                            .onErrorReturn {
                                if (it is ResetException) {
                                    ErrorRequestAction(it.error)
                                } else {
                                    ErrorRequestAction(context.getString(R.string.something_gone_wrong))
                                }
                            }
                }
    }

    class ResetException(val error: String) : Exception()

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
