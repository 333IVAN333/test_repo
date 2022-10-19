package com.bet_planet.android.data.api

import com.bet_planet.android.BuildConfig
import com.bet_planet.android.data.bonuses.BonusDto
import com.bet_planet.android.data.casino.dto.CasinoTokenResponse
import com.bet_planet.android.data.casino.requests.GetCasinoTokenRequest
import com.bet_planet.android.data.categories.CategorySportEventDto
import com.bet_planet.android.data.coupon.booking.CreateCouponBookingRequest
import com.bet_planet.android.data.coupon.booking.dto.CouponBookedDtoResponse
import com.bet_planet.android.data.coupon.booking.dto.CouponBookingDetailsDtoResponse
import com.bet_planet.android.data.coupon.dto.BetPlacedDto2
import com.bet_planet.android.data.coupon.dto.BetPlacedDtoResponse
import com.bet_planet.android.data.coupon.dto.BetPlacedItem
import com.bet_planet.android.data.finance.api.*
import com.bet_planet.android.data.finance.api.requests.*
import com.bet_planet.android.data.freeBets.FreeBetDto
import com.bet_planet.android.data.gameHistory.api.CashOutRequest
import com.bet_planet.android.data.kyc.GetUserInfoRequest
import com.bet_planet.android.data.kyc.docUpload.*
import com.bet_planet.android.data.kyc.email.SetUserEmailRequest
import com.bet_planet.android.data.kyc.email.SetUserPhoneRequest
import com.bet_planet.android.data.kyc.name.SetUserNameRequest
import com.bet_planet.android.data.leagues.LeagueDto
import com.bet_planet.android.data.matches.SportEventDto
import com.bet_planet.android.data.matches.SportGroupDto
import com.bet_planet.android.data.registration.dto.*
import com.bet_planet.android.data.registration.sms.ResendSmsCodeRequest
import com.bet_planet.android.data.registration.sms.SendSmsCodeRequest
import com.bet_planet.android.data.registration.sms.SendSmsCodeResponse
import com.bet_planet.android.data.sports.SportDto
import com.bet_planet.android.data.transactions.GetTransactionsCountRequest
import com.bet_planet.android.data.transactions.GetTransactionsRequest
import com.bet_planet.android.data.transactions.TransactionsCountResponseDto
import com.bet_planet.android.data.transactions.TransactionsResponseDto
import com.bet_planet.android.data.user.logout.LogoutRequest
import com.bet_planet.android.domain.homePage.matches.PeriodLoadingMatches
import com.bet_planet.android.domain.homePage.matches.SportEvent
import com.bet_planet.android.domain.homePage.matches.StatusLoadSportEvent
import com.bet_planet.android.presentation.gameHistory.dto.CashOutDetails
import com.bet_planet.android.presentation.gameHistory.dto.GameHistoryItem
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import retrofit2.http.Header as HeaderField

const val API_VERSION = "v1.0.0"
const val BASE_PATH = "/sportsbook/rest"
const val COMMON_PATH = "/sportsbook/rest/v2"

const val MARKET_GROUP_BASIC = "basic"

@SuppressWarnings("Detekt.TooManyFunctions")
interface CommonApi {

    @GET(BuildConfig.VERSION_CHECK_URL)
    fun getMinimumVersion(): Single<MinVersionResponse>

    @GET
    fun getStoreLocations(
            @Url url: String
    ): Single<StoreLocatorResponse?>

    @GET("$COMMON_PATH/tree?eventType=Match")
    fun getSportsTree(
            @Query("ln") language: String,
            @Query("status") status: String?
    ): Single<List<SportDto>>

    @GET("$COMMON_PATH/tree?eventType=Match")
    fun getSportsTreeForSportId(
            @Query("ln") language: String,
            @Query("sportId") sportId: Int,
            @Query("status") status: String
    ): Single<List<SportDto>>

    /**
     * @param status  Статус спортивных событий может быть двух вариантов: Live и Upcoming
     * @param language Язык установленный в системе
     * @param to параметр, указывающий на период за который нужно грузить данные. Пример: PT24H
     */
    @Headers(
            //"Accept-Encoding: gzip, deflate",
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json"
    )
    @GET("$COMMON_PATH/matches")
    fun getAllSportEvents(
            @Query("sportId") sportIds: List<Int>,
            @Query("eventType") eventType: String = "Match",
            @Query("status") status: String = StatusLoadSportEvent.LIVE.status,
            @Query("ln") language: String,
            @Query("to") to: String = PeriodLoadingMatches.NONE.period
    ): Single<List<SportEventDto>>

    @GET("$COMMON_PATH/matches")
    fun getAllSportEventsByLeagues(
            @Query("leagueId") leaguesIds: List<Int>,
            @Query("eventType") eventType: String? = SportEvent.MATCH_EVENT_TYPE,
            @Query("status") status: String = StatusLoadSportEvent.LIVE.status,
            @Query("ln") language: String
    ): Single<List<SportEventDto>>

    /**
     * Возвращает список самых популярных матчей
     */
    @GET("$COMMON_PATH/matches/filter/{filterId}")
    fun getPopularSportEvents(
            @Path("filterId") filterId: String,
            @Query("marketGroup") market: String = MARKET_GROUP_BASIC,
            @Query("ln") language: String
    ): Single<List<SportEventDto?>?>

    @GET("$COMMON_PATH/matches")
    fun getSportEventsBySport(
            @Query("sportId") sportId: String,
            @Query("leagueId") leagueId: String? = null,
            @Query("status") status: String = StatusLoadSportEvent.LIVE.status,
            @Query("marketGroup") market: String = MARKET_GROUP_BASIC,
            @Query("to") period: String? = null,
            @Query("ln") language: String
    ): Single<List<SportEventDto?>?>

    @GET("$COMMON_PATH/matches/{id}")
    fun getMatchById(
            @Path("id") id: String,
            @Query("ln") language: String
    ): Single<SportEventDto?>

    @GET("$COMMON_PATH/matches/{id}/results")
    fun getEndedMatchById(
            @Path("id") id: String,
            @Query("ln") language: String
    ): Single<SportEventDto?>

    @GET("$COMMON_PATH/matches")
    fun getMatchesByIds(
            @Query("matchId") ids: List<Int>,
            @Query("marketGroup") market: String = MARKET_GROUP_BASIC,
            @Query("eventType") eventType: String = "Match",
            @Query("ln") ln: String
    ): Single<List<SportEventDto>>

    @GET("$COMMON_PATH/groups")
    fun getGroupsBySport(
            @Query("sportId") sportId: String,
            @Query("ln") ln: String
    ): Single<List<SportGroupDto?>?>

    @GET("$COMMON_PATH/leagues")
    fun getLeagues(
            @Query("leagueId") args: List<Int> = listOf(),
            @Query("ln") ln: String
    ): Single<List<LeagueDto?>?>

}

interface SingularApi {

    @FormUrlEncoded
    @POST("WebsiteService")
    fun getCasinoToken(@FieldMap request: GetCasinoTokenRequest): Single<CasinoTokenResponse>

    @FormUrlEncoded
    @POST("WebsiteService")
    fun checkTelephone(@FieldMap request: CheckTelephoneRequest, @HeaderField("Cookie") authCookie: String = ""): Single<BetResponse>

    @FormUrlEncoded
    @POST("WebsiteService")
    fun confirmChangeUserTelephone(@FieldMap request: SetUserPhoneRequest, @HeaderField("Cookie") authCookie: String = ""): Single<BetResponse>

    @FormUrlEncoded
    @POST("WebsiteService")
    fun login(@FieldMap request: LoginRequest): Single<LoginResponse>

    @FormUrlEncoded
    @POST("WebsiteService")
    fun loginOtp(@FieldMap request: LoginOtpRequest): Single<BetResponse>
}

interface SingularWithCookieApi {

    @FormUrlEncoded
    @POST("WebsiteService")
    fun getCasinoToken(
            @FieldMap request: GetCasinoTokenRequest,
            @HeaderField("Cookie") authCookie: String
    ): Single<CasinoTokenResponse>

    @FormUrlEncoded
    @POST("WebsiteService")
    fun getServiceAuthToken(@FieldMap request: GetServiceAuthTokenRequest, @HeaderField("Cookie") authCookie: String): Single<GetTokenResponse>

    @FormUrlEncoded
    @POST("WebsiteService")
    fun getBalance(@FieldMap request: GetBalanceRequest, @HeaderField("Cookie") authCookie: String): Single<BalanceResponse>

    @FormUrlEncoded
    @POST("WebsiteService")
    fun getTransactions(
            @FieldMap request: GetTransactionsRequest,
            @HeaderField("Cookie") authCookie: String
    ): Single<TransactionsResponseDto>

    @FormUrlEncoded
    @POST("WebsiteService")
    fun getTransactionsCount(
            @FieldMap request: GetTransactionsCountRequest,
            @HeaderField("Cookie") authCookie: String)
            : Single<TransactionsCountResponseDto>

}
