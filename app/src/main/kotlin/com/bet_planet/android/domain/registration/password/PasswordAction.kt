package com.bet_planet.android.domain.registration.password

import com.bet_planet.android.domain.registration.dto.Country
import com.bet_planet.components.domain.Action

sealed class PasswordAction : Action

data class StartLoginAction(val phone: String, val country: Country, val password: String) : PasswordAction()

data class InitPasswordResetAction(val phone: String, val country: Country) : PasswordAction()

data class ResetPasswordAction(val phone: String, val userID: String, val smsCode: String, val newPass: String, val countryCode: String) : PasswordAction()

data class ErrorOnPasswordScreenAction(val msg: String) : PasswordAction()

object FinishRequestAction : PasswordAction()

object ClearPasswordErrorAction : PasswordAction()

data class EmptyPasswordErrorAction(val msg: String) : PasswordAction()

data class RestoreAction(
        val state: PasswordState
) : PasswordAction()
