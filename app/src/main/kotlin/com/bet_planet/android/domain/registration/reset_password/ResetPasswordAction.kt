package com.bet_planet.android.domain.registration.reset_password

import com.bet_planet.components.domain.Action

sealed class ResetPasswordAction : Action

data class StartTimerRetryAction(val delayInSeconds: Int) : ResetPasswordAction()

data class TimerAction(val seconds: Int) : ResetPasswordAction()

data class SuccessCodeAction(val success: SuccessSmsCodeResponse) :
        ResetPasswordAction()

data class ErrorRequestAction(val error: String) : ResetPasswordAction()

object ResetErrorAction : ResetPasswordAction()

data class ErrorPasswordAction(val msg: String) : ResetPasswordAction()

data class SendResetPasswordAction(
        val phone: String,
        val userID: String,
        val smsCode: String,
        val newPass: String,
        val countryCode: String) : ResetPasswordAction()

object FinishRequestAction : ResetPasswordAction()

data class ResendSmsCodeAction(val guid: String) : ResetPasswordAction()

data class RequestVerificationCode(val phone: String) : ResetPasswordAction()

data class RequestResetPasswordCode(val phone: String) : ResetPasswordAction()

object ShowSmsCodeError : ResetPasswordAction()

object FinishRegisterRequestAction : ResetPasswordAction()

data class RequestRegisterUserAction(
        val phone: String,
        val countryCode: String,
        val pass: String,
        val smsCode: String,
        val countryId: String,
        val birthDate: Long
) : ResetPasswordAction()

data class RestoreAction(
        val state: ResetPasswordState
) : ResetPasswordAction()

object Idle : ResetPasswordAction()
