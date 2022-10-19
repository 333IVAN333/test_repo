package com.bet_planet.android.data.registration.password

import com.bet_planet.android.domain.registration.password.PasswordSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class PasswordDataModule {

    @Binds
    @Singleton
    internal abstract fun bindsPasswordSource(source: PasswordDataSource): PasswordSource

}
