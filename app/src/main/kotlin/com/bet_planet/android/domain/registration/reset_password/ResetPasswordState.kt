package com.bet_planet.android.domain.registration.reset_password

import com.bet_planet.android.domain.registration.dto.Country
import com.bet_planet.components.domain.State

data class ResetPasswordState(
        val phone: String,
        val country: Country,
        val password:String,
        val loadingFirst: Boolean,
        val loadingRequestCode: Boolean,
        val loadingRegister: Boolean,
        val secondsToRetry: Int,
        val error: String,
        val errorPassword: String,
        val codeFormatError: Boolean = false,
        val birthDate: Long,
        val userId: String
) : State
