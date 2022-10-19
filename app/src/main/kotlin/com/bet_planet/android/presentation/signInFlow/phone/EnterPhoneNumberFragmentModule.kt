package com.bet_planet.android.presentation.signInFlow.phone

import androidx.fragment.app.Fragment
import com.bet_planet.components.domain.Mapper
import com.bet_planet.android.domain.registration.phone.EnterPhoneNumberState
import dagger.Binds
import dagger.Module

@Module
interface EnterPhoneNumberFragmentModule {

    @Binds
    fun bindsFragment(fragment: EnterPhoneNumberFragment): Fragment

    @Binds
    fun bindsStateMapper(mapper: EnterPhoneNumberStateMapper):
            Mapper<EnterPhoneNumberState, EnterPhoneNumberParcelable>

    @Binds
    fun bindsParcelableMapper(mapper: EnterPhoneNumberParcelableMapper):
            Mapper<EnterPhoneNumberParcelable, EnterPhoneNumberState>
}
