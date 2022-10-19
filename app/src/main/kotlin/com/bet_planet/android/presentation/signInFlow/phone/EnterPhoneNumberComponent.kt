package com.bet_planet.android.presentation.signInFlow.phone

import com.bet_planet.dagger.viewmodel.ComponentBuilder
import com.bet_planet.dagger.viewmodel.ProviderViewModelComponent
import com.bet_planet.dagger.viewmodel.ViewModelComponentBuilder
import com.bet_planet.dagger.viewmodel.ViewModelKey
import com.bet_planet.android.domain.registration.phone.EnterPhoneNumberState
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Module(subcomponents = [EnterPhoneNumberComponent.EnterPhoneNumberSubComponent::class])
interface EnterPhoneNumberComponent {

    @Binds
    @IntoMap
    @ViewModelKey(EnterPhoneNumberViewModel::class)
    fun bindViewModelBuilder(builder: EnterPhoneNumberComponent.EnterPhoneNumberSubComponent.Builder): ComponentBuilder

    @Subcomponent(modules = [EnterPhoneNumberViewModelModule::class])
    interface EnterPhoneNumberSubComponent : ProviderViewModelComponent<EnterPhoneNumberViewModel> {
        @Subcomponent.Builder
        interface Builder :
            ViewModelComponentBuilder<EnterPhoneNumberState, EnterPhoneNumberViewModel, EnterPhoneNumberSubComponent>
    }
}
