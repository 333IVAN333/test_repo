package com.bet_planet.android.presentation.signInFlow.reset_password

import com.bet_planet.android.domain.registration.reset_password.ResetPasswordDomainModule
import dagger.Module

@Module(includes = [ResetPasswordDomainModule::class])
interface ResetPasswordViewModelModule
