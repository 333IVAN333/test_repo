package com.bet_planet.android.domain.registration.reset_password

import com.google.gson.annotations.SerializedName

sealed class VerifySmsCodeResponse

data class ErrorSmsCodeResponse(
    val error: String,
    val throwable: Throwable
) : VerifySmsCodeResponse()

data class SuccessSmsCodeResponse(
    @SerializedName("refreshToken") val refreshToken: String = "",
    @SerializedName("accessToken") val accessToken: String = "",
    @SerializedName("user") val user: UserInfoResponse? = UserInfoResponse()
) : VerifySmsCodeResponse()

data class UserInfoResponse(
    @SerializedName("id") val id: String = "",
    @SerializedName("userRole") val userRole: Int = -1,
    @SerializedName("alias") val alias: String = "",
    @SerializedName("notificationCount") val notificationCount: Int = 0,
    @SerializedName("isMe") val isMe: Boolean = false,
    @SerializedName("hasFamilyProducts") val hasFamilyProducts: Boolean = false,
    @SerializedName("birthdate") val birthDateString: String = "",
    @SerializedName("crmId") val crmId: String = ""
)
