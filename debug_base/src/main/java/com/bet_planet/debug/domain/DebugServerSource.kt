package com.bet_planet.debug.domain

import com.bet_planet.feature.FeatureTogglable
import io.reactivex.Single

interface DebugServerSource {
    fun getUrlId(default: Int): Int

    fun setDebugMode(id: Int)

    fun setSslStatus(status: Boolean)

    fun sslStatus(): Boolean

    fun reboot()

    fun isSessionChange(): Single<Boolean>

    fun removeSessionChangeFlag()

    fun changeFeatureToggle(toggle: FeatureTogglable)
}
