package com.bet_planet.android.domain.registration.reset_password

import android.os.Parcelable
import com.bet_planet.android.domain.registration.dto.Country
import com.bet_planet.android.presentation.signInFlow.reset_password.VerifySmsCodeMode
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResetPasswordArguments(
        val phone: String,
        val country: Country,
        val birthDate: Long = 0,
        val password: String = "",
        val checkType: VerifySmsCodeMode,
        val userId: String = ""
) : Parcelable
