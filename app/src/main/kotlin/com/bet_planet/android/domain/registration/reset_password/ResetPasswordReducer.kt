package com.bet_planet.android.domain.registration.reset_password

import com.bet_planet.components.domain.Reducer
import javax.inject.Inject

class ResetPasswordReducer @Inject constructor() :
        Reducer<ResetPasswordState, ResetPasswordAction> {

    companion object {
        const val DEFAULT_SECONDS_TIMER = -1
    }

    override fun reduce(
            state: ResetPasswordState,
            action: ResetPasswordAction
    ): ResetPasswordState = when (action) {
        is TimerAction -> state.copy(secondsToRetry = action.seconds)
        is SuccessCodeAction -> state
        is ErrorRequestAction -> state.copy(error = action.error, loadingRegister = false)
        is ResetErrorAction -> state.copy(error = "", codeFormatError = false, errorPassword = "")
        is ResendSmsCodeAction -> state.copy(secondsToRetry = DEFAULT_SECONDS_TIMER)
        is StartTimerRetryAction -> state.copy(loadingRequestCode = false, secondsToRetry = action.delayInSeconds)
        is RestoreAction -> action.state
        is RequestVerificationCode -> state.copy(loadingRequestCode = true)
        is ShowSmsCodeError -> state.copy(codeFormatError = true)
        is RequestRegisterUserAction -> state.copy(loadingRegister = true)
        is FinishRegisterRequestAction -> state.copy(loadingRegister = false, error = "")
        is RequestResetPasswordCode -> state.copy(loadingRequestCode = true)
        is ErrorPasswordAction -> state.copy(errorPassword = action.msg)
        is SendResetPasswordAction -> state.copy(loadingRegister = true, errorPassword = "")
        FinishRequestAction -> state.copy(loadingRegister = true, errorPassword = "")
        Idle -> state
    }
}
