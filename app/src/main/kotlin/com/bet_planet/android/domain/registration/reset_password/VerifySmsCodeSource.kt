package com.bet_planet.android.domain.registration.reset_password

import com.bet_planet.android.data.registration.dto.BetResponse
import io.reactivex.Observable
import io.reactivex.Single

interface VerifySmsCodeSource {

    fun startTimer(delayInSeconds: Int): Observable<Int>

    fun sendSmsCode(smsCode: String): Single<VerifySmsCodeResponse>

    fun resendSmsCode(guid: String): Single<BetResponse>

    fun requestPhoneVerificationCode(phone: String): Single<BetResponse>

    fun registerUser(phone: String, pass: String, countryId: String, birthDate: String, smsCode: String): Single<BetResponse>
}
