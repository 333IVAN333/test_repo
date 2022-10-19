package com.bet_planet.android.presentation.signInFlow.password

import android.content.Context
import com.bet_planet.android.R
import com.bet_planet.android.domain.main.BackAction
import com.bet_planet.android.domain.main.ClearChainAction
import com.bet_planet.android.domain.main.MainAction
import com.bet_planet.android.domain.main.MainState
import com.bet_planet.android.domain.navigation.Screen
import com.bet_planet.android.domain.registration.password.*
import com.bet_planet.android.domain.utils.CheckUtil
import com.bet_planet.components.android.BaseViewModel
import com.bet_planet.components.domain.Model
import com.bet_planet.components.domain.Schedulers
import com.bet_planet.components.domain.log.ApplicationLogger
import com.bet_planet.dagger.viewmodel.ViewModelSubComponentBuilderFactory
import javax.inject.Inject

class PasswordViewModel @Inject constructor(
        val viewModelComponentFactory: ViewModelSubComponentBuilderFactory,
        schedulers: Schedulers,
        logger: ApplicationLogger,
        private val context: Context,
        model: Model<PasswordState, PasswordAction>,
        private val mainModel: Model<MainState, MainAction>
) : BaseViewModel<PasswordAction, PasswordState>(schedulers, logger, model) {

    fun backAction() {
        mainModel.actions.accept(BackAction)
    }

    fun closeFlow() {
        mainModel.actions.accept(
                ClearChainAction(
                        Screen.ENTER_PHONE_NUMBER)
        )
    }

    fun actionClick(currentMode: PasswordMode, password: String) {
        if (currentMode == PasswordMode.MODE_ENTER) {
            model().state.blockingFirst().let {
                model().actions.accept(
                        StartLoginAction(
                                phone = it.phone,
                                country = it.country,
                                password = password
                        )
                )
            }
        } else if (currentMode == PasswordMode.MODE_CREATE_NEW) {
            if (!CheckUtil.validPassword(password)) {
                model().actions.accept(ErrorOnPasswordScreenAction(context.getString(R.string.password_conditions)))
                return
            }
            model().state.blockingFirst().let {
                model().actions.accept(
                        ResetPasswordAction(
                                phone = it.phone,
                                userID = it.userId,
                                smsCode = it.smsCode,
                                newPass = password,
                                countryCode = it.country.code.toString()
                        )
                )
            }
        }
    }

    fun restorePasswordClick() {
        model().state.blockingFirst().let {
            model().actions.accept(
                    InitPasswordResetAction(
                            phone = it.phone,
                            country = it.country
                    )
            )
        }
    }

    fun clearPasswordError() {
        model().state.blockingFirst().let {
            if (!it.errorPassword.isNullOrEmpty()) {
                model().actions.accept(ClearPasswordErrorAction)
            }
        }
    }

    fun showEmptyPasswordError() {
        model().actions.accept(EmptyPasswordErrorAction(context.getString(R.string.required_field)))
    }
}
