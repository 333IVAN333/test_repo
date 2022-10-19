package com.bet_planet.android.presentation.utils

import android.content.Context
import com.bet_planet.android.R
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.collections.HashMap

class DecimalFormatter {

    companion object {

        private val currenciesMap = HashMap<Int, String>().apply {
            put(4, "EUR")
            put(95, "mBT")
            put(96, "uBT")
            put(16, "GHC")
            put(1, "KZT")
            put(18, "MGA")
            put(100, "VRT")
        }

        private val BALANCE_FORMATTER = DecimalFormat("#,###.##").apply {
            val balanceSymbols: DecimalFormatSymbols = decimalFormatSymbols
            balanceSymbols.groupingSeparator = ' '
            balanceSymbols.decimalSeparator = '.'

            roundingMode = RoundingMode.DOWN
            maximumFractionDigits = 2
            decimalFormatSymbols = balanceSymbols
        }

        fun formatBetValue(context: Context, betValue: Double): String {
            val formatFromString = context.getString(R.string.bet_value_format)
            return String.format(Locale.US, formatFromString, betValue)
        }

        fun getCurrencySign(currencyCode: Int): String {
            return currenciesMap[currencyCode] ?: ""
        }

        fun formatPriceWithoutFractionDigits(value: Double, currencyCode: Int): String {
            try {
                return BALANCE_FORMATTER.format((value / 100).toLong()) + " " + getCurrencySign(currencyCode)
            } catch (e: Exception) {
                //TODO DENIS log
            }
            return "-"
        }

        fun formatPriceWithTwoFractionDigits(value: Double, currencyCode: Int): String {
            try {
                return String.format(Locale.US, "%.2f", value / 100) + " " + getCurrencySign(currencyCode)
            } catch (e: Exception) {
                //TODO DENIS log
            }
            return "-"
        }

        fun formatAmountWithTwoFractionDigits(value: Double): String {
            try {
                return String.format(Locale.US, "%.2f", value / 100)
            } catch (e: Exception) {
                //TODO DENIS log
            }
            return "-"
        }

        fun formatOnlyDigitPrice(value: Double): String {
            try {
                return BALANCE_FORMATTER.format(value / 100)
            } catch (e: Exception) {
                //TODO DENIS log
            }
            return ""
        }

        fun formatPrice(value: Double, currencyCode: Int): String {
            try {
                return BALANCE_FORMATTER.format(value / 100) + " " + getCurrencySign(currencyCode)
            } catch (e: Exception) {
                //TODO DENIS log
            }
            return "-"
        }

        fun formatOdds(value: String): String {
            try {
                val doubleValue = value.toDouble()
                return formatOdds(doubleValue)
            } catch (e: Exception) {
                //TODO DENIS log
            }
            return "-"
        }

        fun formatOdds(value: Double): String {
            try {
                // Чтобы не было округления в текстовом представлении с двумя знаками после запятой
                // выведем немного больше знаков и обрежем до нужного количества
                val result = String.format(Locale.US, "%.5f", value)
                return result.substring(0, result.length - 3)
            } catch (e: Exception) {
                //TODO DENIS log
            }
            return "-"
        }

        fun formatPrice(value: BigDecimal): String {
            try {
                return BALANCE_FORMATTER.format(value)
            } catch (e: Exception) {
                //TODO DENIS log
            }
            return "-"
        }
    }
}
