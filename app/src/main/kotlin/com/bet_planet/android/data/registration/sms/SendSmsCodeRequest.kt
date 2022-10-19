package com.bet_planet.android.data.registration.sms

import com.google.gson.annotations.SerializedName

data class SendSmsCodeRequest(
    @SerializedName("smsCode") val smsCode: String,
    @SerializedName("guid") val guid: String
)

data class ResendSmsCodeRequest(@SerializedName("guid") val guid: String)
