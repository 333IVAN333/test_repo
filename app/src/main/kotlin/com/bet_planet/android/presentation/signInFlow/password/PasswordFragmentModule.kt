package com.bet_planet.android.presentation.signInFlow.password

import androidx.fragment.app.Fragment
import com.bet_planet.android.domain.registration.password.PasswordState
import com.bet_planet.components.domain.Mapper
import dagger.Binds
import dagger.Module

@Module
interface PasswordFragmentModule {

    @Binds
    fun bindsFragment(fragment: PasswordFragment): Fragment

    @Binds
    fun bindsStateMapper(mapper: PasswordStateMapper):
            Mapper<PasswordState, PasswordParcelable>

    @Binds
    fun bindsParcelableMapper(mapper: PasswordParcelableMapper):
            Mapper<PasswordParcelable, PasswordState>
}
