package com.bet_planet.android.data.registration.sms

import com.google.gson.annotations.SerializedName

data class SendSmsCodeResponse(
    @SerializedName("refreshToken") val refreshToken: String?,
    @SerializedName("accessToken") val accessToken: String?,
    @SerializedName("user") val user: UserResponse?
)

data class UserResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("userRole") val userRole: Int?,
    @SerializedName("alias") val alias: String?,
    @SerializedName("notificationCount") val notificationCount: Int?,
    @SerializedName("isMe") val isMe: Boolean?,
    @SerializedName("hasFamilyProducts") val hasFamilyProducts: Boolean?,
    @SerializedName("birthdate") val birthDateString: String?,
    @SerializedName("crmId") val crmId: String?
)
