package com.bet_planet.android.presentation.signInFlow.reset_password

import com.bet_planet.android.domain.registration.reset_password.ResetPasswordState
import com.bet_planet.android.domain.registration.reset_password.SuccessSmsCodeResponse
import com.bet_planet.android.domain.registration.reset_password.UserInfoResponse
import com.bet_planet.components.domain.Mapper
import io.reactivex.Single
import javax.inject.Inject

class ResetPasswordStateMapper @Inject constructor(
) : Mapper<ResetPasswordState, ResetPasswordParcelable> {
    override fun map(from: ResetPasswordState): Single<ResetPasswordParcelable> =
            Single.fromCallable {
                ResetPasswordParcelable(
                        loadingFirst = from.loadingFirst, //TODO DENIS выставить прогрессы
                        secondsToRetry = from.secondsToRetry,
                        error = from.error,
                        codeFormatError = from.codeFormatError,
                        phone = from.phone,
                        loadingRequestCode = from.loadingRequestCode,
                        loadingRegister = from.loadingRegister,
                        password = from.password,
                        errorPassword = from.errorPassword,
                        country = from.country,
                        birthDate = from.birthDate,
                        userId = from.userId
                )
            }
}

class SuccesSendCodeStateMapper @Inject constructor(
) : Mapper<SuccessSmsCodeResponse, SuccessSmsCodeParcelable> {
    override fun map(from: SuccessSmsCodeResponse): Single<SuccessSmsCodeParcelable> =
            Single.fromCallable {
                SuccessSmsCodeParcelable(
                        refreshToken = from.refreshToken,
                        accessToken = from.accessToken,
                        user = null
                )
            }
}

class UserStateMapper @Inject constructor() :
        Mapper<UserInfoResponse, UserInfoParcelable> {
    override fun map(from: UserInfoResponse): Single<UserInfoParcelable> =
            Single.fromCallable {
                UserInfoParcelable(
                        id = from.id,
                        userRole = from.userRole,
                        alias = from.alias,
                        notificationCount = from.notificationCount,
                        isMe = from.isMe,
                        hasFamilyProducts = from.hasFamilyProducts,
                        birthDateString = from.birthDateString
                )
            }
}

class SuccessSendCodeParcelableMapper @Inject constructor(
) : Mapper<SuccessSmsCodeParcelable, SuccessSmsCodeResponse> {
    override fun map(from: SuccessSmsCodeParcelable): Single<SuccessSmsCodeResponse> =
            Single.fromCallable {
                SuccessSmsCodeResponse(
                        refreshToken = from.refreshToken,
                        accessToken = from.accessToken,
                        user = null
                )
            }
}

class UserParcelableMapper @Inject constructor() :
        Mapper<UserInfoParcelable, UserInfoResponse> {
    override fun map(from: UserInfoParcelable): Single<UserInfoResponse> =
            Single.fromCallable {
                UserInfoResponse(
                        id = from.id,
                        userRole = from.userRole,
                        alias = from.alias,
                        notificationCount = from.notificationCount,
                        isMe = from.isMe,
                        hasFamilyProducts = from.hasFamilyProducts,
                        birthDateString = from.birthDateString
                )
            }
}

class VerifySmsCodeParcelableMapper @Inject constructor(
) : Mapper<ResetPasswordParcelable, ResetPasswordState> {
    override fun map(from: ResetPasswordParcelable): Single<ResetPasswordState> =
            Single.fromCallable {
                ResetPasswordState(
                        loadingFirst = from.loadingFirst, //TODO DENIS выставить прогрессы
                        secondsToRetry = from.secondsToRetry,
                        error = from.error,
                        phone = from.phone,
                        codeFormatError = from.codeFormatError,
                        loadingRequestCode = from.loadingRequestCode,
                        loadingRegister = from.loadingRegister,
                        errorPassword = from.errorPassword,
                        country = from.country,
                        password = from.password,
                        birthDate = from.birthDate,
                        userId = from.userId
                )
            }
}


