package com.bet_planet.android.data.registration.sms

import com.bet_planet.android.data.api.CommonApi
import com.bet_planet.android.data.api.ParseExceptionSource
import com.bet_planet.android.data.api.SingularApi
import com.bet_planet.android.data.registration.dto.BetResponse
import com.bet_planet.android.data.registration.dto.GetTelVerificationCodeRequest
import com.bet_planet.android.data.registration.dto.RegisterUserRequest
import com.bet_planet.android.domain.registration.reset_password.ErrorSmsCodeResponse
import com.bet_planet.android.domain.registration.reset_password.SuccessSmsCodeResponse
import com.bet_planet.android.domain.registration.reset_password.VerifySmsCodeResponse
import com.bet_planet.android.domain.registration.reset_password.VerifySmsCodeSource
import com.bet_planet.components.domain.Mapper
import com.bet_planet.components.domain.Schedulers
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class VerifySmsCodeDataSource @Inject constructor(
        private val api: CommonApi,
        private val singularApi: SingularApi,
        private val parseException: ParseExceptionSource,
        private val mapper: Mapper<SendSmsCodeResponse, SuccessSmsCodeResponse>,
        private val schedulers: Schedulers
) : VerifySmsCodeSource {

    companion object {
        const val DELAY_TO_MILLISECONDS: Long = 1000
    }

    override fun sendSmsCode(smsCode: String): Single<VerifySmsCodeResponse> {
        return api.sendSmsCode(SendSmsCodeRequest(smsCode, ""))
                .flatMap<VerifySmsCodeResponse> {
                    mapper.map(it)
                }
                .onErrorReturn {
                    ErrorSmsCodeResponse(
                            error = parseException.parseException(it),
                            throwable = it
                    )
                }
    }

    override fun resendSmsCode(guid: String): Single<BetResponse> =
            api.resendSmsCode(ResendSmsCodeRequest(guid))

    override fun requestPhoneVerificationCode(phone: String): Single<BetResponse> {
        return singularApi.getTelVerificationCode(GetTelVerificationCodeRequest(phone))
    }

    override fun registerUser(phone: String, pass: String, countryId: String, birthDate: String, smsCode: String): Single<BetResponse> {
        return singularApi.registerUser(RegisterUserRequest(phone, pass, birthDate, smsCode, countryId))
    }

    override fun startTimer(delayInSeconds: Int): Observable<Int> {
        return Observable.range(1, delayInSeconds)
                .concatMap {
                    Observable.just(delayInSeconds - it)
                            .delay(DELAY_TO_MILLISECONDS, TimeUnit.MILLISECONDS, schedulers.io())
                }
    }
}
