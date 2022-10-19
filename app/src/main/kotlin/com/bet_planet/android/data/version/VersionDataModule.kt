package com.bet_planet.android.data.version

import com.bet_planet.android.domain.homePage.VersionSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class VersionDataModule {

    @Binds
    @Singleton
    internal abstract fun bindsVersionSource(source: VersionDataSource): VersionSource
}