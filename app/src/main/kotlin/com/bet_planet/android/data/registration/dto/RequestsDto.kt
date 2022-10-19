package com.bet_planet.android.data.registration.dto

import com.bet_planet.android.BuildConfig
import com.bet_planet.android.data.utils.locale.LocaleUtil
import java.util.*

data class CheckTelephoneRequest(val phoneNum: String
) : HashMap<String, String>() {
    init {
        put("req", "checkTelephone")
        put("TelephoneNumber", phoneNum)
    }
}

data class LoginRequest(
        val phoneNum: String,
        val password: String
) : HashMap<String, String>() {
    init {
        put("req", "login")
        put("userIdentifier", phoneNum)
        put("password", password)
        put("otpDeliveryChannel", 0.toString())
    }
}

data class LoginOtpRequest(
        val phoneNum: String,
        val otp: String
) : HashMap<String, String>() {
    init {
        put("req", "loginOtp")
        put("userIdentifier", phoneNum)
        put("otp", otp)
        put("loginType", 2.toString())
    }
}

data class GetSmsCodeRequest(
        val phoneNum: String
) : HashMap<String, String>() {
    init {
        put("req", "getSmsCode")
        put("userIdentifier", phoneNum)
        put("channelType", 2.toString())
    }
}

data class GetTelVerificationCodeRequest(
        val phoneNum: String
) : HashMap<String, String>() {
    init {
        put("req", "getTelVerificationCode")
        put("tel", phoneNum)
        put("channelType", 2.toString())
        put("userLang", LocaleUtil.getCurrentLocale().language)
    }
}

data class RegisterUserRequest(
        val phoneNum: String,
        val password: String,
        val birthDate: String,
        val smsCode: String,
        val countryId: String
) : HashMap<String, String>() {
    init {
        put("req", "registerUser")
        put("Profile", "by_phone")
        put("AcceptedTerm", "1")
        put("Password", password)
        put("CountryID", countryId)
        put("TelephoneNumber", phoneNum)
        put("BirthDate", birthDate)
        put("TelVerifyCode", smsCode)
        put("UserLanguage", LocaleUtil.getCurrentLocale().language)
        put("PreferredCurrencyID", BuildConfig.PREFERRED_CURRENCY)
    }
}

class GetListTosRequest : HashMap<String, String>() {
    init {
        put("req", "getListOfTermsAndConditions")
        put("filterByTermID", 7.toString())
    }
}

data class InitPasswordResetRequest(
        val phoneNum: String
) : HashMap<String, String>() {
    init {
        put("req", "initPasswordReset")
        put("userIdentifier", phoneNum)
    }
}

data class ResetPasswordRequest(
        val userId: String,
        val smsCode: String,
        val newPass: String
) : HashMap<String, String>() {
    init {
        put("req", "resetPassword")
        put("userID", userId)
        put("confirmCode", smsCode)
        put("newPassword", newPass)
    }
}

data class ChangePasswordRequest(
        val userId: String,
        val oldPass: String,
        val newPass: String
) : HashMap<String, String>() {
    init {
        put("req", "changePassword")
        put("userID", userId)
        put("oldPassword", oldPass)
        put("newPassword", newPass)
    }
}

data class GetResetPasswordCodeRequest(
        val phoneNum: String
) : HashMap<String, String>() {
    init {
        put("req", "getPasswordResetCode")
        put("address", phoneNum)
        put("userIdentifier", phoneNum)
        put("channelType", 2.toString())
    }
}

data class GetActiveSessionRequest(
        val userId: String
) : HashMap<String, String>() {
    init {
        put("req", "isSessionActive")
        put("userID", userId)
    }
}

data class GetUserLangRequest(
        val userId: String
) : HashMap<String, String>() {
    init {
        put("req", "getUserLang")
        put("userID", userId)
    }
}

data class ChangeUserLangRequest(
        val userId: String,
        val newLang: String
) : HashMap<String, String>() {
    init {
        put("req", "changeLang")
        put("userID", userId)
        put("langCode", newLang)
    }
}

data class GetServiceAuthTokenRequest(
        val userId: String,
        val provider: String = BuildConfig.PROVIDER_ID
) : HashMap<String, String>() {
    init {
        put("req", "getServiceAuthToken")
        put("userID", userId)
        put("providerID", provider)
    }
}

data class GetBalanceRequest(
        val userId: String
) : HashMap<String, String>() {
    init {
        put("req", "getBalance")
        put("userID", userId)
        put("isSingle", "0")
    }
}

