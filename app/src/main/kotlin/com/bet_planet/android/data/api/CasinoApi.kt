package com.bet_planet.android.data.api

import com.bet_planet.android.BuildConfig
import com.bet_planet.android.data.casino.dto.CasinoCategoryCollectionDto
import com.bet_planet.android.data.casino.dto.CasinoComponentDto
import com.bet_planet.android.data.casino.dto.CasinoDtoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface CasinoApi {

    // sample image url
    // https://static.betplanet.com/website/game-directory/thumbnail/regular/light/busy/1060.jpg
    //
    //

    @GET("/api/v1/pages/${BuildConfig.CASINO_PATH}?locale=en")
    fun getCasino(): Single<CasinoDtoResponse>

    @GET("/api/v1/pages/${BuildConfig.CASINO_LIVE_PATH}?locale=en")
    fun getLiveCasino(): Single<CasinoDtoResponse>

    @GET("/api/v2/game-collections/{collectionId}")
    fun getCollectionDetails(
            @Path("collectionId") collectionId: String
    ): Single<CasinoCategoryCollectionDto>

    @GET("/api/v1/components/{componentId}?locale=en")
    fun getComponentDetails(
            @Path("componentId") componentId: String
    ): Single<CasinoComponentDto>

    @GET("/api/v1/pages/betgames?locale=en")
    fun getBetGames(): Single<CasinoDtoResponse>

    @GET("/api/v1/pages/virtuals?locale=en")
    fun getVirtualGames(): Single<CasinoDtoResponse>

    @GET("/api/v2/pages/spin-2-win?locale=en")
    fun getSpin2Win(): Single<CasinoDtoResponse>

    @GET("/api/v2/pages/virtual-epl?locale=en")
    fun getVirtualEpl(): Single<CasinoDtoResponse>

}