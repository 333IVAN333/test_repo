package com.bet_planet.android.domain.registration.reset_password

import com.bet_planet.components.domain.Interactor
import com.bet_planet.components.domain.Schedulers
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import javax.inject.Inject

class SmsCodeRetryTimerInteractor @Inject constructor(
        private val verifySmsCodeSource: VerifySmsCodeSource,
        private val schedulers: Schedulers
) : Interactor<ResetPasswordAction, ResetPasswordState> {
    override fun bind(
            actions: Observable<ResetPasswordAction>,
            state: Observable<ResetPasswordState>
    ): Observable<ResetPasswordAction> = actions.ofType<StartTimerRetryAction>()
            .observeOn(schedulers.io())
            .flatMap { verifySmsCodeSource.startTimer(it.delayInSeconds) }
            .map { TimerAction(it) }
}
