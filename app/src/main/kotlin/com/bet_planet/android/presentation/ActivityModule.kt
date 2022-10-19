package com.bet_planet.android.presentation

import com.bet_planet.android.data.storelocator.StoreLocationsDataModule
import com.bet_planet.android.domain.storelocator.StoreLocatorSource
import com.bet_planet.android.presentation.main.MainActivity
import com.bet_planet.android.presentation.main.MainActivityModule
import com.bet_planet.android.presentation.storelocator.StoreLocatorActivity
import com.bet_planet.debug.presentation.DebugActivity
import com.bet_planet.debug.presentation.DebugActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector(
            modules = [
                MainActivityModule::class,
                MainFragmentModule::class
            ]
    )
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(
//            modules = [
//                StoreLocationsDataModule::class
//            ]
    )
    fun contributeStoreLocatorActivity(): StoreLocatorActivity

    @ContributesAndroidInjector(
        modules = [
            DebugActivityModule::class
        ]
    )
    fun contributeDebugActivity(): DebugActivity
}
