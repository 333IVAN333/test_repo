package com.bet_planet.android.domain.registration.reset_password

import com.bet_planet.components.domain.ArgumentContainer
import com.bet_planet.components.domain.StateProvider
import javax.inject.Inject

class ResetPasswordStateProvider @Inject constructor(
        private val argumentContainer: ArgumentContainer
) :
        StateProvider<ResetPasswordState> {
    override fun get(): ResetPasswordState {
        val args = argumentContainer.argument(ResetPasswordArguments::class.java)
        return ResetPasswordState(
                phone = args.phone,
                loadingRequestCode = false,
                loadingFirst = false,
                secondsToRetry = ResetPasswordReducer.DEFAULT_SECONDS_TIMER,
                error = "",
                country = args.country,
                password = args.password,
                loadingRegister = false,
                birthDate = args.birthDate,
                userId = args.userId,
                errorPassword = "",
                codeFormatError = false
        )
    }
}
