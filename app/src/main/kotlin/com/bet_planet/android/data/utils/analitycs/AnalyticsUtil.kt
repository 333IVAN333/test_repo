package com.bet_planet.android.data.utils.analitycs

import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import com.bet_planet.android.data.coupon.dto.BetPlacedItem
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalyticsUtil @Inject constructor(private val context: Context) {

    companion object {

        const val EVENT_BET_SUCCESSFUL = "bet_Successful"
        const val EVENT_BET_ERROR = "bet_Error"
        const val EVENT_LOGIN_SUCCESS = "login_success"
        const val EVENT_LOGIN_ERROR = "login_error"
        const val EVENT_PAYMENT_METHOD = "paymentMethod_open"

        const val PARAM_AMOUNT = "amount"
        const val PARAM_IS_QUICK_BET = "is_quick_bet"
        const val PARAM_ERROR_CODE = "errorCode"
        const val PARAM_AUTO = "auto"
        const val PARAM_SOURCE = "source"
    }

    fun logEvent(event: String, params: Bundle? = null) {
        FirebaseAnalytics.getInstance(context).logEvent(event, params)
    }

    fun logErrorBetEvent(code: Int, isQuickBet: Boolean = false) {
        logEvent(
                EVENT_BET_ERROR,
                bundleOf(
                        PARAM_ERROR_CODE to code,
                        PARAM_IS_QUICK_BET to isQuickBet
                )
        )
    }

    fun logSuccessBetEvent(betList: List<BetPlacedItem>, isQuickBet: Boolean = false) {
        val sum = betList.sumByDouble {
            it.betAmount.toDouble()
        }
        logEvent(
                EVENT_BET_SUCCESSFUL,
                bundleOf(
                        PARAM_AMOUNT to sum,
                        PARAM_IS_QUICK_BET to isQuickBet
                )
        )
    }

    fun logSuccessLogin(isAutoLogin: Boolean = false) {
        logEvent(
                EVENT_LOGIN_SUCCESS,
                bundleOf(PARAM_AUTO to isAutoLogin)
        )
    }

    fun logErrorLogin(code: Int, isAutoLogin: Boolean = false) {
        logEvent(
                EVENT_LOGIN_ERROR,
                bundleOf(
                        PARAM_AUTO to isAutoLogin,
                        PARAM_ERROR_CODE to code
                )
        )
    }
}