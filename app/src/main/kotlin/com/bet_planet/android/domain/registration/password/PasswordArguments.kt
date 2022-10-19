package com.bet_planet.android.domain.registration.password

import android.os.Parcelable
import com.bet_planet.android.domain.registration.dto.Country
import com.bet_planet.android.presentation.signInFlow.password.PasswordMode
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PasswordArguments(
        val phone: String,
        val country: Country,
        val mode: PasswordMode,
        val userId: String = "",
        val smsCode: String = ""
) : Parcelable
