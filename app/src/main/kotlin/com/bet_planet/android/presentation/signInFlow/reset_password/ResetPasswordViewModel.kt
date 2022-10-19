package com.bet_planet.android.presentation.signInFlow.reset_password

import android.content.Context
import com.bet_planet.android.R
import com.bet_planet.android.domain.main.BackAction
import com.bet_planet.android.domain.main.ClearChainAction
import com.bet_planet.android.domain.main.MainAction
import com.bet_planet.android.domain.navigation.Screen
import com.bet_planet.android.domain.registration.reset_password.*
import com.bet_planet.android.domain.utils.CheckUtil
import com.bet_planet.components.android.BaseViewModel
import com.bet_planet.components.domain.Model
import com.bet_planet.components.domain.Schedulers
import com.bet_planet.components.domain.log.ApplicationLogger
import com.jakewharton.rxrelay2.Relay
import javax.inject.Inject

class ResetPasswordViewModel @Inject constructor(
        schedulers: Schedulers,
        logger: ApplicationLogger,
        model: Model<ResetPasswordState, ResetPasswordAction>,
        private val context: Context,
        private val mainActions: Relay<MainAction>
) : BaseViewModel<ResetPasswordAction, ResetPasswordState>(schedulers, logger, model) {

    fun resetError() {
        model().state.blockingFirst().run {
            if (codeFormatError || error.isNotEmpty() || errorPassword.isNotEmpty()) {
                model().actions.accept(ResetErrorAction)
            }
        }
    }

    fun closeFlow() {
        mainActions.accept(
                ClearChainAction(Screen.ENTER_PHONE_NUMBER)
        )
    }

    fun restartTimer() {
        model().actions.accept(StartTimerRetryAction(60))
    }

    fun requestVerificationCode() {
        resetError()
        model().state.blockingFirst().let {
            model().actions.accept(
                    RequestVerificationCode(
                            phone = "${it.country.code}${it.phone}"
                    )
            )
        }
    }

    fun requestResetPasswordCode() {
        resetError()
        model().state.blockingFirst().let {
            model().actions.accept(
                    RequestResetPasswordCode(
                            phone = "${it.country.code}${it.phone}"
                    )
            )
        }
    }

    fun nextClick(smsCode: String, password: String) {
        resetError()
        if (!CheckUtil.validPassword(password)) {
            model().actions.accept(ErrorPasswordAction(context.getString(R.string.password_conditions)))
            return
        }
        if (smsCode.isNotEmpty()) {
            model().state.blockingFirst().let {
                model().actions.accept(
                        SendResetPasswordAction(
                                phone = it.phone,
                                userID = it.userId,
                                smsCode = smsCode,
                                newPass = password,
                                countryCode = it.country.code.toString()
                        )
                )
            }
        } else {
            model().actions.accept(ShowSmsCodeError)
        }
    }

    fun back() {
        mainActions.accept(BackAction)
    }
}
