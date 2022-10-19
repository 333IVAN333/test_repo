package com.bet_planet.android.presentation.signInFlow.password

import com.bet_planet.android.domain.registration.password.PasswordDomainModule
import com.bet_planet.android.domain.registration.phone.EnterPhoneNumberDomainModule
import dagger.Module

@Module(includes = [PasswordDomainModule::class])
interface PasswordViewModelModule
