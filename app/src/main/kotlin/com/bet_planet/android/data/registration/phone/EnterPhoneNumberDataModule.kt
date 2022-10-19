package com.bet_planet.android.data.registration.phone

import com.bet_planet.android.data.registration.dto.BetResponse
import com.bet_planet.android.domain.registration.phone.CheckPhoneResponse
import com.bet_planet.android.domain.registration.phone.EnterPhoneNumberSource
import com.bet_planet.components.domain.Mapper
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class EnterPhoneNumberDataModule {

    @Binds
    @Singleton
    internal abstract fun bindsEnterPhoneNumberSource(source: EnterPhoneNumberDataSource): EnterPhoneNumberSource

    @Binds
    @Singleton
    internal abstract fun bindsDataMapper(mapper: CheckPhoneDataMapper): Mapper<BetResponse, CheckPhoneResponse>
}
