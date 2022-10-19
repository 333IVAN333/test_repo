package com.bet_planet.android.domain.registration.password

import com.bet_planet.components.domain.Interactor
import com.bet_planet.components.domain.Reducer
import com.bet_planet.components.domain.StateProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import dagger.multibindings.Multibinds

@Module
abstract class PasswordDomainModule {

    @Binds
    internal abstract fun bindsReducer(reducer: PasswordReducer):
            Reducer<PasswordState, PasswordAction>

    @Binds
    internal abstract fun bindsStateProvider(provider: PasswordStateProvider):
            StateProvider<PasswordState>

    @Multibinds
    internal abstract fun multiBindsInteractors():
            Set<@JvmSuppressWildcards Interactor<PasswordAction, PasswordState>>

    @Binds
    @IntoSet
    internal abstract fun bindsStartLoginInteractor(interactor: StartLoginInteractor):
            Interactor<PasswordAction, PasswordState>

    @Binds
    @IntoSet
    internal abstract fun bindsInitPasswordResetInteractor(interactor: InitPasswordResetInteractor):
            Interactor<PasswordAction, PasswordState>

}
