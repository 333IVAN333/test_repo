package com.bet_planet.android.data.api

import com.bet_planet.android.R
import com.bet_planet.android.data.net.NoInternetException
import com.bet_planet.android.domain.resources.StringResources
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class ParseExceptionDataSource @Inject constructor(val context: StringResources) : ParseExceptionSource {

    override fun parseException(throwable: Throwable): String {
        var message = context.getString(R.string.error_common)
        val result = parseNetworkException(throwable)
        if (result != 0) {
            message = context.getString(result)
        } else if (checkApiException(throwable)) {
            val apiException = throwable as ApiException
            val description = apiException.description ?: ""
            message = if (description.isNotEmpty()) {
                description
            } else {
                context.getString(R.string.error_common)
            }
        }
        return message
    }

    private fun checkApiException(throwable: Throwable): Boolean {
        return throwable is ApiException
    }

    private fun parseNetworkException(throwable: Throwable): Int {
        // Set here by default non valid string resource id
        var messageResId = 0
        if (checkNoInternet(throwable)) {
            messageResId = R.string.error_no_internet
        } else if (checkNetworkException(throwable)) {
            messageResId = R.string.error_connection
        }
        return messageResId
    }

    private fun checkNoInternet(throwable: Throwable): Boolean {
        return throwable is NoInternetException
    }

    private fun checkNetworkException(throwable: Throwable): Boolean {
        return throwable is UnknownHostException ||
            throwable is TimeoutException ||
            throwable is SocketException ||
            throwable is SocketTimeoutException
    }
}
