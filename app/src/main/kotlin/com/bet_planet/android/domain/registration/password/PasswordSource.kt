package com.bet_planet.android.domain.registration.password

import com.bet_planet.android.data.registration.dto.BetResponse
import io.reactivex.Observable
import io.reactivex.Single

interface PasswordSource {

    fun initPasswordReset(phone:String): Single<BetResponse>

    fun getResetCode(phone:String): Single<BetResponse>

    fun resetPassword(userId: String, smsCode: String, newPass: String): Single<BetResponse>

    fun changePassword(userId: String, oldPass: String, newPass: String): Single<BetResponse>
}
