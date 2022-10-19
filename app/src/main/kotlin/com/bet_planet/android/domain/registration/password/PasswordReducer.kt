package com.bet_planet.android.domain.registration.password

import com.bet_planet.components.domain.Reducer
import javax.inject.Inject

class PasswordReducer @Inject constructor() :
        Reducer<PasswordState, PasswordAction> {
    override fun reduce(
            state: PasswordState,
            action: PasswordAction
    ): PasswordState = when (action) {
        is StartLoginAction -> state.copy(loading = true, errorPassword = "")
        is InitPasswordResetAction -> state.copy(loading = true, errorPassword = "")
        is ResetPasswordAction -> state.copy(loading = true, errorPassword = "")
        is RestoreAction -> action.state
        is FinishRequestAction -> state.copy(loading = false, errorPassword = "")
        is ErrorOnPasswordScreenAction -> state.copy(errorPassword = action.msg, loading = false)
        is ClearPasswordErrorAction -> state.copy(errorPassword = "")
        is EmptyPasswordErrorAction -> state.copy(errorPassword = action.msg)
    }
}
