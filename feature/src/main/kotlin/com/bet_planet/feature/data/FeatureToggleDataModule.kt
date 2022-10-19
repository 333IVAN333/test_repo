package com.bet_planet.feature.data

import com.bet_planet.feature.FeatureToggleSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class FeatureToggleDataModule {

    @Binds
    @Singleton
    internal abstract fun bindsFeatureToggleSource(
        source: SharedFeatureToggleSource
    ): FeatureToggleSource
}
