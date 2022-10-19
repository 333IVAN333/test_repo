package com.bet_planet.android.data.api

import com.bet_planet.android.data.about_us.dto.SectionDetailsDto
import com.bet_planet.android.data.about_us.dto.SectionDto
import com.bet_planet.android.data.promotions.dto.PromotionDto
import com.bet_planet.android.data.promotions.dto.PromotionsListResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface CmsApi {

    @GET("api/v2/Web/countries")
    fun getAllCountries(
            @Header("Cookie") authCookie: String
    ): Single<ResponseBody>

    // @GET("promotions/mg/en/main.json")
    @GET("api/v2/Web/promotions")
    fun getAllPromotionsForCountryId(
            @Query("singularCountryId") countryId: Int,
            @Query("localeCode") localeCode: String
    ): Single<ArrayList<PromotionDto>>

    @GET("api/v2/Promotion/all/{countryId}")
    fun getAllPromotionsForCountryId2(
            @Path("countryId") countryId: Int,
            @Header("Cookie") authCookie: String
    ): Single<PromotionsListResponse>

    @GET("api/v2/Web/sections")
    fun getSections(
            @Query("singularCountryId") countryId: Int,
            @Query("localeCode") localeCode: String
    ): Single<ArrayList<SectionDto?>?>

    @GET("api/v2/Web/document/{idDocument}")
    fun getDocument(
            @Path("idDocument") idDocument: Int,
            @Query("singularCountryId") countryId: Int,
            @Query("localeCode") localeCode: String
    ): Single<SectionDetailsDto?>

}