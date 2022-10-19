package com.bet_planet.android.domain.registration.login

import com.bet_planet.android.data.registration.dto.BetResponse
import com.bet_planet.android.data.registration.dto.LoginResponse
import io.reactivex.Single

interface LoginSource {

    fun login(phone: String, pass: String): Single<LoginResponse>

    fun getActiveSession(userId: String): Single<BetResponse>
}
