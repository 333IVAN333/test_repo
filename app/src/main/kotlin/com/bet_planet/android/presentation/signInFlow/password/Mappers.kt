package com.bet_planet.android.presentation.signInFlow.password

import com.bet_planet.android.domain.registration.password.PasswordState
import com.bet_planet.android.domain.registration.phone.EnterPhoneNumberState
import com.bet_planet.components.domain.Mapper
import io.reactivex.Single
import javax.inject.Inject

class PasswordStateMapper @Inject constructor() : Mapper<PasswordState, PasswordParcelable> {
    override fun map(from: PasswordState): Single<PasswordParcelable> =
            Single.fromCallable {
                PasswordParcelable(
                        country = from.country,
                        phone = from.phone,
                        errorPassword = from.errorPassword,
                        loading = from.loading,
                        userId = from.userId,
                        smsCode = from.smsCode
                )
            }
}

class PasswordParcelableMapper @Inject constructor() : Mapper<PasswordParcelable, PasswordState> {
    override fun map(from: PasswordParcelable): Single<PasswordState> =
            Single.fromCallable {
                PasswordState(
                        country = from.country,
                        phone = from.phone,
                        errorPassword = from.errorPassword,
                        loading = from.loading,
                        userId = from.userId,
                        smsCode = from.smsCode
                )
            }
}
