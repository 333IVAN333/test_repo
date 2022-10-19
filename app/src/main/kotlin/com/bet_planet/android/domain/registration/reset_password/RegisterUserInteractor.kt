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
import com.bet_planet.android.domain.user.auth.UserAuthSource
import com.bet_planet.components.domain.Interactor
import com.bet_planet.components.domain.Schedulers
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.ofType
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

private const val SHOW_DIALOG_SERVER_CODE = 103
private const val WRONG_PHONE_SERVER_CODE = 100

private val errorCodes = setOf(
        SHOW_DIALOG_SERVER_CODE,
        WRONG_PHONE_SERVER_CODE
)

class RegisterUserInteractor @Inject constructor(
        private val verifySmsCodeSource: VerifySmsCodeSource,
        private val loginSource: LoginSource,
        private val schedulers: Schedulers,
        private val userAuthSource: UserAuthSource,
        private val context: Context,
        private val mainActions: Relay<MainAction>,
        private val analyticsUtil: AnalyticsUtil
) : Interactor<ResetPasswordAction, ResetPasswordState> {

    @SuppressLint("SimpleDateFormat")
    override fun bind(
            actions: Observable<ResetPasswordAction>,
            state: Observable<ResetPasswordState>
    ): Observable<ResetPasswordAction> {
        return actions.ofType<RequestRegisterUserAction>()
                .observeOn(schedulers.io())
                .flatMapSingle { action ->
                    verifySmsCodeSource.registerUser(
                            "${action.countryCode}${action.phone}",
                            action.pass,
                            action.countryId,
                            SimpleDateFormat("yyyy-MM-dd").format(Date(action.birthDate)),
                            action.smsCode)
                            .flatMap {
                                if (it.code != 10) {
                                    throw RegisterException()
                                }
                                loginSource.login("${action.countryCode}${action.phone}", action.pass)

                            }
                            .map {
                                if (it.isSuccessLogin()) {
                                    analyticsUtil.logSuccessLogin()
                                    userAuthSource.apply {
                                        phoneCountryCode = action.countryCode
                                        userId = it.userId!!
                                        phoneNumber = action.phone
                                        password = action.pass
                                    }
                                    userAuthSource.lastUsedTime = 0
                                    mainActions.accept(
                                            NavigateAction(Screen.QUESTION_PIN_CODE, null)
                                    )
                                } else {
                                    analyticsUtil.logErrorLogin(it.errorCode)
                                    ErrorRequestAction(context.getString(R.string.error_register_text))
                                }
                            }
                            .onErrorReturn {
                                ErrorRequestAction(context.getString(R.string.error_register_text))
                            }
                }
                .map {
                    if (it is ResetPasswordAction) {
                        it
                    } else {
                        FinishRegisterRequestAction
                    }
                }

    }

    private class RegisterException : Exception()

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
