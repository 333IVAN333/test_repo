package com.bet_planet.android.domain.registration.password

import android.annotation.SuppressLint
import android.content.Context
import com.bet_planet.android.R
import com.bet_planet.android.data.registration.dto.UserLangResponse
import com.bet_planet.android.data.utils.analitycs.AnalyticsUtil
import com.bet_planet.android.data.utils.locale.LocaleUtil
import com.bet_planet.android.domain.kyc.UserInfoSource
import com.bet_planet.android.domain.main.MainAction
import com.bet_planet.android.domain.main.NavigateAction
import com.bet_planet.android.domain.main.UserIsLoggedInAction
import com.bet_planet.android.domain.main.handlerApiException
import com.bet_planet.android.domain.navigation.Screen
import com.bet_planet.android.domain.registration.login.LoginSource
import com.bet_planet.android.domain.user.auth.UserAuthSource
import com.bet_planet.android.domain.utils.CommonStorage
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

class StartLoginInteractor @Inject constructor(
        private val loginSource: LoginSource,
        private val schedulers: Schedulers,
        private val userAuthSource: UserAuthSource,
        private val mainActions: Relay<MainAction>,
        private val context: Context,
        private val analyticsUtil: AnalyticsUtil,
        private val userInfoSource: UserInfoSource,
        private val commonStorage: CommonStorage
) : Interactor<PasswordAction, PasswordState> {

    @SuppressLint("SimpleDateFormat")
    override fun bind(
            actions: Observable<PasswordAction>,
            state: Observable<PasswordState>
    ): Observable<PasswordAction> {
        return actions.ofType<StartLoginAction>()
                .observeOn(schedulers.io())
                .flatMapSingle { action ->
                    loginSource.login("${action.country.code}${action.phone}", action.password)
                            .flatMap { response ->
                                Single.just(response)
                            }
                            .map {
                                if (it.isSuccessLogin()){
                                    analyticsUtil.logSuccessLogin()
                                    userAuthSource.apply {
                                        phoneCountryCode = action.country.code.toString()
                                        userId = it.userId!!
                                        phoneNumber = action.phone
                                        password = action.password
                                    }
                                    userAuthSource.lastUsedTime = 0

                                    mainActions.accept(UserIsLoggedInAction)
                                    mainActions.accept(
                                            NavigateAction(Screen.QUESTION_PIN_CODE, null)
                                    )
                                    FinishRequestAction
                                } else if (it.errorCode == 113) {
                                    analyticsUtil.logErrorLogin(it.errorCode)
                                    ErrorOnPasswordScreenAction(context.getString(R.string.password_incorrect))
                                } else {
                                    analyticsUtil.logErrorLogin(it.errorCode)
                                    //TODO DENIS change text
                                    ErrorOnPasswordScreenAction(context.getString(R.string.something_gone_wrong))
                                }
                            }.flatMap {resultAction ->
                                if (resultAction is FinishRequestAction) {
                                    userInfoSource
                                            .loadUserLang()
                                            .onErrorReturn {
                                                UserLangResponse(0, LocaleUtil.getCurrentLocale().language)
                                            }
                                            .map { langResponse ->
                                                commonStorage.putString(CommonStorage.PREF_SELECTED_LANGUAGE, langResponse.language, true)
                                                resultAction
                                            }
                                } else {
                                    Single.just(resultAction)
                                }
                            }
                            .onErrorReturn {
                                ErrorOnPasswordScreenAction(context.getString(R.string.something_gone_wrong)) //TODO DENIS change text
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
