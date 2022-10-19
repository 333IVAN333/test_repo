package com.bet_planet.android.presentation.signInFlow.password

import android.os.Parcelable
import com.bet_planet.android.domain.registration.dto.Country
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PasswordParcelable(
        val country: Country,
        val errorPassword: String?,
        val loading: Boolean,
        val phone: String,
        val userId: String,
        val smsCode: String
) : Parcelable
