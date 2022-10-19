package com.bet_planet.android.data.registration.sms

import com.bet_planet.components.domain.Mapper
import com.bet_planet.android.domain.registration.reset_password.SuccessSmsCodeResponse
import com.bet_planet.android.domain.registration.reset_password.UserInfoResponse
import io.reactivex.Single
import javax.inject.Inject

class SendSmsCodeResponseMapper @Inject constructor(
    private val mapper: Mapper<UserResponse, UserInfoResponse>
) : Mapper<SendSmsCodeResponse, SuccessSmsCodeResponse> {
    override fun map(from: SendSmsCodeResponse): Single<SuccessSmsCodeResponse> =
        Single.fromCallable {
            SuccessSmsCodeResponse(
                refreshToken = from.refreshToken ?: "",
                accessToken = from.accessToken ?: "",
                user = mapper
                    .map(from.user ?: getEmptyUser())
                    .blockingGet()
            )
        }

    private fun getEmptyUser() =
        UserResponse(
            id = "",
            userRole = 0,
            alias = "",
            notificationCount = 0,
            isMe = false,
            hasFamilyProducts = false,
            birthDateString = "",
            crmId = ""
        )
}

class UserInfoMapper @Inject constructor() :
    Mapper<UserResponse, UserInfoResponse> {
    override fun map(from: UserResponse): Single<UserInfoResponse> = Single.fromCallable {
        UserInfoResponse(
            id = from.id ?: "",
            userRole = from.userRole ?: 0,
            alias = from.alias ?: "",
            notificationCount = from.notificationCount ?: 0,
            isMe = from.isMe ?: false,
            hasFamilyProducts = from.hasFamilyProducts ?: false,
            birthDateString = from.birthDateString ?: "",
            crmId = from.crmId ?: ""
        )
    }
}
