package com.bet_planet.android.domain.registration.reset_password

import com.bet_planet.android.domain.main.MainAction
import com.bet_planet.android.domain.main.handlerApiException
import com.bet_planet.android.domain.time.TimeSource
import com.bet_planet.components.domain.Interactor
import com.bet_planet.components.domain.Schedulers
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import javax.inject.Inject

class ResendSmsCodeInteractor @Inject constructor(
    private val verifySmsCodeSource: VerifySmsCodeSource,
    private val schedulers: Schedulers,
    private val timeSource: TimeSource,
    val mainActions: Relay<MainAction>
) : Interactor<ResetPasswordAction, ResetPasswordState> {

    override fun bind(
            actions: Observable<ResetPasswordAction>,
            state: Observable<ResetPasswordState>
    ): Observable<ResetPasswordAction> = actions.ofType<ResendSmsCodeAction>()
        .observeOn(schedulers.io())
        .flatMapMaybe {
            verifySmsCodeSource.resendSmsCode(it.guid)
                .handlerApiException(mainActions)
                .map {
                    StartTimerRetryAction(60)
                }
                .toMaybe()
                .onErrorComplete()
        }
}
