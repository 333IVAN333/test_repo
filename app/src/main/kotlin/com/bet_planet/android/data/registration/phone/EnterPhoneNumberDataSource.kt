package com.bet_planet.android.data.registration.phone

import com.bet_planet.android.data.api.ParseExceptionSource
import com.bet_planet.android.data.api.SingularApi
import com.bet_planet.android.data.kyc.email.SetUserPhoneRequest
import com.bet_planet.android.data.registration.dto.BetResponse
import com.bet_planet.android.data.registration.dto.CheckTelephoneRequest
import com.bet_planet.android.domain.registration.phone.CheckPhoneResponse
import com.bet_planet.android.domain.registration.phone.EnterPhoneNumberSource
import com.bet_planet.android.domain.user.auth.UserAuthSource
import com.bet_planet.components.domain.Mapper
import io.reactivex.Single
import javax.inject.Inject

class EnterPhoneNumberDataSource @Inject constructor(
        private val api: SingularApi,
        private val parseException: ParseExceptionSource,
        private val userAuthSource: UserAuthSource,
        private val mapper: Mapper<BetResponse, CheckPhoneResponse>
) : EnterPhoneNumberSource {

    override fun checkPhone(phone: String): Single<CheckPhoneResponse> {
        return api
                .checkTelephone(CheckTelephoneRequest(phoneNum = phone), authCookie = userAuthSource.cookieAuth)
                .flatMap { mapper.map(it) }
    }

    override fun confirmChangePhone(request: SetUserPhoneRequest): Single<BetResponse> {
        return  api
                .confirmChangeUserTelephone(request, authCookie = userAuthSource.cookieAuth)
    }
}
