package com.bet_planet.android.presentation.signInFlow.reset_password

import com.bet_planet.android.domain.registration.reset_password.ResetPasswordState
import com.bet_planet.dagger.viewmodel.ComponentBuilder
import com.bet_planet.dagger.viewmodel.ProviderViewModelComponent
import com.bet_planet.dagger.viewmodel.ViewModelComponentBuilder
import com.bet_planet.dagger.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Module(subcomponents = [ResetPasswordComponent.ResetPasswordSubComponent::class])
interface ResetPasswordComponent {

    @Binds
    @IntoMap
    @ViewModelKey(ResetPasswordViewModel::class)
    fun bindViewModelBuilder(builder: ResetPasswordSubComponent.Builder): ComponentBuilder

    @Subcomponent(modules = [ResetPasswordViewModelModule::class])
    interface ResetPasswordSubComponent : ProviderViewModelComponent<ResetPasswordViewModel> {
        @Subcomponent.Builder
        interface Builder :
            ViewModelComponentBuilder<ResetPasswordState, ResetPasswordViewModel, ResetPasswordSubComponent>
    }
}
