package com.bet_planet.android.domain.registration.password

import com.bet_planet.android.domain.registration.dto.Country
import com.bet_planet.components.domain.ArgumentContainer
import com.bet_planet.components.domain.State
import com.bet_planet.components.domain.StateProvider
import javax.inject.Inject

data class PasswordState(
        val country: Country,
        val errorPassword: String?,
        val loading: Boolean,
        val phone: String,
        val userId: String,
        val smsCode: String
) : State

class PasswordStateProvider @Inject constructor(
        private val argumentContainer: ArgumentContainer
) : StateProvider<PasswordState> {
    override fun get(): PasswordState {
        val args = argumentContainer.argument(PasswordArguments::class.java)
        return PasswordState(
                country = args.country,
                loading = false,
                errorPassword = "",
                phone = args.phone,
                userId = args.userId,
                smsCode = args.smsCode
        )
    }
}
