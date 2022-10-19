package com.bet_planet.android.domain.registration.reset_password

import com.bet_planet.components.domain.Interactor
import com.bet_planet.components.domain.Reducer
import com.bet_planet.components.domain.StateProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import dagger.multibindings.Multibinds

@Module
abstract class ResetPasswordDomainModule {

    @Binds
    internal abstract fun bindsReduser(reduser: ResetPasswordReducer):
            Reducer<ResetPasswordState, ResetPasswordAction>

    @Binds
    internal abstract fun bindsStateProvider(provider: ResetPasswordStateProvider):
            StateProvider<ResetPasswordState>

    @Multibinds
    internal abstract fun multiBindsInteractors():
            Set<@JvmSuppressWildcards Interactor<ResetPasswordAction, ResetPasswordState>>

    @Binds
    @IntoSet
    internal abstract fun bindsTimerRetryCodeInteractor(interactor: SmsCodeRetryTimerInteractor):
            Interactor<ResetPasswordAction, ResetPasswordState>

    @Binds
    @IntoSet
    internal abstract fun bindsRequestVerificationCodeInteractor(interactor: RequestVerificationCodeInteractor):
            Interactor<ResetPasswordAction, ResetPasswordState>

    @Binds
    @IntoSet
    internal abstract fun bindsRequestPasswordResetCodeInteractor(interactor: RequestPasswordResetCodeInteractor):
            Interactor<ResetPasswordAction, ResetPasswordState>

    @Binds
    @IntoSet
    internal abstract fun bindsResendSmsCodeInteractor(interactor: ResendSmsCodeInteractor):
            Interactor<ResetPasswordAction, ResetPasswordState>

    @Binds
    @IntoSet
    internal abstract fun bindsErrorRequestInteractor(interactor: ErrorRequestInteractor):
            Interactor<ResetPasswordAction, ResetPasswordState>

    @Binds
    @IntoSet
    internal abstract fun bindsRegisterUserInteractor(interactor: RegisterUserInteractor):
            Interactor<ResetPasswordAction, ResetPasswordState>

    @Binds
    @IntoSet
    internal abstract fun bindsSetNewPasswordInteractor(interactor: ResetPasswordInteractor):
            Interactor<ResetPasswordAction, ResetPasswordState>
}
