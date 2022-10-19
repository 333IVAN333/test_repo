package com.bet_planet.android.presentation

import com.bet_planet.android.presentation.main.MainComponent
import com.bet_planet.debug.presentation.DebugComponent
import dagger.Module

@Module(includes = [MainComponent::class, DebugComponent::class])
interface ViewModelModule
