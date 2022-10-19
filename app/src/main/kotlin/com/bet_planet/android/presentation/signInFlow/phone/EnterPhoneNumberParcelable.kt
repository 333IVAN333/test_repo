package com.bet_planet.android.presentation.signInFlow.phone

import android.os.Parcelable
import com.bet_planet.android.domain.registration.dto.Country
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EnterPhoneNumberParcelable(
        val country: Country?,
        val errorPhone: String?,
        val errorCountry: String?,
        val loading: Boolean,
        val phone: String,
        val cursorPosition: Int
) : Parcelable
