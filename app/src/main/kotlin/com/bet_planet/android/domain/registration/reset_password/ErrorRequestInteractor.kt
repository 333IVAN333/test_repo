package com.bet_planet.android.domain.registration.reset_password

import com.bet_planet.android.domain.main.Message
import com.bet_planet.android.domain.main.MessageBus
import com.bet_planet.android.domain.main.MessageIcon
import com.bet_planet.components.domain.Interactor
import com.bet_planet.components.domain.Schedulers
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import javax.inject.Inject

class ErrorRequestInteractor @Inject constructor(
        private val schedulers: Schedulers,
        private val messageBus: MessageBus
) : Interactor<ResetPasswordAction, ResetPasswordState> {
    override fun bind(
            actions: Observable<ResetPasswordAction>,
            state: Observable<ResetPasswordState>
    ): Observable<ResetPasswordAction> = actions.ofType<ErrorRequestAction>()
            .observeOn(schedulers.io())
            .map {
                messageBus.sendMessage(
                        Message(
                                title = it.error,
                                icon = MessageIcon.SOMETHING_GONE_WRONG
                        )
                )
            }
            .map { Idle }
}
