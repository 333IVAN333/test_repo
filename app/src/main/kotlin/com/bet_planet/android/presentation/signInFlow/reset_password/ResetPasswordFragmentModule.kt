package com.bet_planet.android.presentation.signInFlow.reset_password

import androidx.fragment.app.Fragment
import com.bet_planet.android.domain.registration.reset_password.ResetPasswordState
import com.bet_planet.components.domain.Mapper
import dagger.Binds
import dagger.Module

@Module
interface ResetPasswordFragmentModule {

    @Binds
    fun bindsFragment(fragment: ResetPasswordFragment): Fragment

    @Binds
    fun bindsStateMapper(mapper: ResetPasswordStateMapper):
            Mapper<ResetPasswordState, ResetPasswordParcelable>

    @Binds
    fun bindsParcelableMapper(mapper: VerifySmsCodeParcelableMapper):
            Mapper<ResetPasswordParcelable, ResetPasswordState>

}
