package com.bet_planet.components.android.log

import android.util.Log
import com.bet_planet.components.android.BuildConfig
import com.bet_planet.components.domain.log.ApplicationLogger
import javax.inject.Inject

class AndroidLogger @Inject constructor() :
    ApplicationLogger {

    companion object {
        private val TAG = AndroidLogger::class.java.simpleName
    }

    override fun logError(error: Throwable) {
        Log.e(TAG, error.message, error)
    }

    override fun logMessage(message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, message)
        }
    }
}
