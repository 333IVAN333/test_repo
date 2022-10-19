package com.bet_planet.android.data.registration.phone

import com.bet_planet.android.data.registration.dto.BetResponse
import com.bet_planet.android.domain.registration.phone.CheckPhoneResponse
import com.bet_planet.components.domain.Mapper
import io.reactivex.Single
import javax.inject.Inject

class CheckPhoneDataMapper @Inject constructor() :
        Mapper<BetResponse, CheckPhoneResponse> {

    override fun map(from: BetResponse): Single<CheckPhoneResponse> =
            Single.fromCallable {
                CheckPhoneResponse(
                        code = from.code
                )
            }
}
