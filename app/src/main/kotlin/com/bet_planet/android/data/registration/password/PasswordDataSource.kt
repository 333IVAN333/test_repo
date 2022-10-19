package com.bet_planet.android.data.registration.password

import com.bet_planet.android.data.api.ParseExceptionSource
import com.bet_planet.android.data.api.SingularApi
import com.bet_planet.android.data.registration.dto.*
import com.bet_planet.android.domain.registration.password.PasswordSource
import com.bet_planet.android.domain.user.auth.UserAuthSource
import io.reactivex.Single
import javax.inject.Inject

class PasswordDataSource @Inject constructor(
        private val userAuthSource: UserAuthSource,
        private val api: SingularApi,
        private val parseException: ParseExceptionSource
) : PasswordSource {

    override fun initPasswordReset(phone: String): Single<BetResponse> {
        return api
                .initPasswordReset(InitPasswordResetRequest(phoneNum = phone))
    }

    override fun getResetCode(phone: String): Single<BetResponse> {
        return api.getResetPasswordCode(GetResetPasswordCodeRequest(phone))
    }

    override fun resetPassword(userId: String, smsCode: String, newPass: String): Single<BetResponse> {
        return api.resetPassword(ResetPasswordRequest(userId, smsCode, newPass))
    }

    override fun changePassword(userId: String, oldPass: String, newPass: String): Single<BetResponse> {
        val userId = userAuthSource.userId
        val cookie = userAuthSource.cookieAuth
        return api.changePassword(ChangePasswordRequest(userId, oldPass, newPass), authCookie = cookie)
    }
}
