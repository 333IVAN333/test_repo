package com.bet_planet.android.data.api

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ParseExceptionDataModule {

    @Binds
    @Singleton
    fun bindsParseException(source: ParseExceptionDataSource): ParseExceptionSource
}
