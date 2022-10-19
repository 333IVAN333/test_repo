package com.bet_planet.android.presentation.signInFlow.phone

import com.bet_planet.android.domain.registration.phone.EnterPhoneNumberState
import com.bet_planet.components.domain.Mapper
import io.reactivex.Single
import javax.inject.Inject

class EnterPhoneNumberStateMapper @Inject constructor() : Mapper<EnterPhoneNumberState, EnterPhoneNumberParcelable> {
    override fun map(from: EnterPhoneNumberState): Single<EnterPhoneNumberParcelable> =
            Single.fromCallable {
                EnterPhoneNumberParcelable(
                        country = from.country,
                        errorPhone = from.errorPhone,
                        errorCountry = from.errorCountry,
                        phone = from.phone,
                        cursorPosition = from.cursorPosition,
                        loading = from.loading
                )
            }
}

class EnterPhoneNumberParcelableMapper @Inject constructor() : Mapper<EnterPhoneNumberParcelable, EnterPhoneNumberState> {
    override fun map(from: EnterPhoneNumberParcelable): Single<EnterPhoneNumberState> =
            Single.fromCallable {
                EnterPhoneNumberState(
                        country = from.country,
                        errorPhone = from.errorPhone,
                        errorCountry = from.errorCountry,
                        phone = from.phone,
                        cursorPosition = from.cursorPosition,
                        loading = from.loading
                )
            }
}
