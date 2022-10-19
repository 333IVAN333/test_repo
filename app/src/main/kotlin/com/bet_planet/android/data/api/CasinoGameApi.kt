package com.bet_planet.android.data.api

import com.bet_planet.android.data.casino.game.dto.CasinoGameInitResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface CasinoGameApi {

    @POST("/api/v1/init")
    fun getCasinoGameInit(
            @Body body: HashMap<String, Any>
    ): Single<CasinoGameInitResponse>

}