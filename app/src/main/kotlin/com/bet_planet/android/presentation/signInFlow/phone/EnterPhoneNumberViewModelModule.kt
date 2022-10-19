package com.bet_planet.android.presentation.signInFlow.phone

import com.bet_planet.android.domain.registration.phone.EnterPhoneNumberDomainModule
import dagger.Module

@Module(includes = [EnterPhoneNumberDomainModule::class])
interface EnterPhoneNumberViewModelModule
