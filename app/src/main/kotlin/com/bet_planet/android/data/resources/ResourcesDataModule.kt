package com.bet_planet.android.data.resources

import com.bet_planet.android.domain.resources.StringResources
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ResourcesDataModule {

    @Binds
    @Singleton
    internal abstract fun bindsStringResources(resources: AndroidStringResources): StringResources
}
