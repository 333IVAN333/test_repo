package com.bet_planet.android.presentation

import com.bet_planet.android.domain.push.FirebaseCloudMessagingService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal interface ServicesModule {

    @ContributesAndroidInjector
    fun fcmService(): FirebaseCloudMessagingService
}