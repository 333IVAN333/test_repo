package com.bet_planet.android.presentation.signInFlow.password

import com.bet_planet.android.domain.registration.password.PasswordState
import com.bet_planet.dagger.viewmodel.ComponentBuilder
import com.bet_planet.dagger.viewmodel.ProviderViewModelComponent
import com.bet_planet.dagger.viewmodel.ViewModelComponentBuilder
import com.bet_planet.dagger.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Module(subcomponents = [PasswordComponent.PasswordSubComponent::class])
interface PasswordComponent {

    @Binds
    @IntoMap
    @ViewModelKey(PasswordViewModel::class)
    fun bindViewModelBuilder(builder: PasswordComponent.PasswordSubComponent.Builder): ComponentBuilder

    @Subcomponent(modules = [PasswordViewModelModule::class])
    interface PasswordSubComponent : ProviderViewModelComponent<PasswordViewModel> {
        @Subcomponent.Builder
        interface Builder :
                ViewModelComponentBuilder<PasswordState, PasswordViewModel, PasswordSubComponent>
    }
}
