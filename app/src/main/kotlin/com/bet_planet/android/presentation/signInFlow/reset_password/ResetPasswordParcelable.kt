package com.bet_planet.android.presentation.signInFlow.reset_password

import android.os.Parcelable
import com.bet_planet.android.domain.registration.dto.Country
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResetPasswordParcelable(
        val phone: String,
        val country: Country,
        val password:String,
        val loadingFirst: Boolean,
        val loadingRequestCode: Boolean,
        val loadingRegister: Boolean,
        val secondsToRetry: Int,
        val error: String,
        val errorPassword: String,
        val codeFormatError: Boolean,
        val birthDate: Long,
        val userId: String
) : Parcelable

@Parcelize
data class SuccessSmsCodeParcelable(
    val refreshToken: String,
    val accessToken: String,
    val user: UserInfoParcelable?
) : Parcelable

@Parcelize
data class UserInfoParcelable(
    val id: String,
    val userRole: Int,
    val alias: String,
    val notificationCount: Int,
    val isMe: Boolean,
    val hasFamilyProducts: Boolean,
    val birthDateString: String
) : Parcelable
