package com.bet_planet.debug.data

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.jakewharton.processphoenix.ProcessPhoenix
import com.bet_planet.debug.domain.DebugServerSource
import com.bet_planet.feature.FeatureTogglable
import io.reactivex.Single
import javax.inject.Inject

open class ServerDataSource @Inject constructor(
    val sharedPreferences: SharedPreferences,
    val context: Context
) : DebugServerSource {

    override fun changeFeatureToggle(toggle: FeatureTogglable) {
        with(sharedPreferences.edit()) {
            putBoolean(toggle.tag, toggle.isToggleOn)
            apply()
        }
    }

    override fun setDebugMode(id: Int) {
        with(sharedPreferences.edit()) {
            putInt(DEBUG_URL_ID, id)
            putBoolean(IS_SESSION_CHANGE, true)
            apply()
        }
    }

    override fun setSslStatus(status: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(WITH_SSL, status)
            apply()
        }
    }

    override fun sslStatus(): Boolean =
        with(sharedPreferences) {
            getBoolean(WITH_SSL, true)
        }

    override fun getUrlId(default: Int): Int {
        with(sharedPreferences) {
            return getInt(DEBUG_URL_ID, default)
        }
    }

    override fun reboot() {
        ProcessPhoenix.triggerRebirth(context, Intent(context, Class.forName(MAIN_ACTIVITY_CLASS)))
    }

    override fun removeSessionChangeFlag() {
        with(sharedPreferences.edit()) {
            putBoolean(IS_SESSION_CHANGE, false)
            apply()
        }
    }

    override fun isSessionChange(): Single<Boolean> =
        Single.fromCallable {
            with(sharedPreferences) {
                return@fromCallable getBoolean(IS_SESSION_CHANGE, false)
            }
        }

    companion object {
        const val MAIN_ACTIVITY_CLASS = "com.bet_planet.android.presentation.main.MainActivity"
        const val DEBUG_URL_ID = "DEBUG_URL_ID"
        const val IS_SESSION_CHANGE = "IS_SESSION_CHANGE"
        const val WITH_SSL = "with_ssl"
    }
}
