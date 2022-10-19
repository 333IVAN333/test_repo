package com.bet_planet.android.presentation.utils

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import com.bet_planet.android.domain.locale.Price
import com.bet_planet.android.presentation.locale.CurrencySymbolConverter
import java.math.BigDecimal

fun getSpannedTextPrice(
    textPrice: String,
    styleInteger: TextAppearanceSpan,
    styleDouble: TextAppearanceSpan
): SpannableStringBuilder {
    val ssBuilder = SpannableStringBuilder(textPrice)

    var positionComma = textPrice.indexOf(",") + 1
    if (positionComma == 0) {
        positionComma = textPrice.indexOf(".") + 1
        if (positionComma == 0) {
            positionComma = textPrice.length - 1
        }
    }

    ssBuilder.setSpan(
        styleInteger,
        0,
        positionComma,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    ssBuilder.setSpan(
        styleDouble,
        positionComma,
        textPrice.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    return ssBuilder
}

fun getSpannedTextPriceFromCurrencyFormatResult(
    textPrice: CurrencyFormatResult,
    styleInteger: TextAppearanceSpan,
    styleDouble: TextAppearanceSpan
): SpannableStringBuilder {
    val text = textPrice.result

    val ssBuilder = SpannableStringBuilder(text)

    var positionComma = text.indexOf(textPrice.decimalSeparator) + 1
    if (positionComma == 0) {
        positionComma = text.length - textPrice.currencySymbolWithLocale.length
    }
    ssBuilder.setSpan(
        styleInteger,
        0,
        positionComma,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    ssBuilder.setSpan(
        styleDouble,
        positionComma,
        text.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    return ssBuilder
}

fun getSpannedTextPrice(
    priceValue: Price,
    styleInteger: TextAppearanceSpan,
    styleDouble: TextAppearanceSpan
): SpannableStringBuilder =
    getSpannedTextPrice(getFormattedTextPrice(priceValue), styleInteger, styleDouble)

fun getFormattedTextPrice(priceValue: Price): String {

    val doit = String
        .format("%.2f", priceValue.value.remainder(BigDecimal.ONE).toDouble())
        .substring(2)
    val currency = CurrencySymbolConverter().convert(priceValue.currency.symbol)
    val doitText = String.format("%1s%2s", doit, currency)
    val textFormattedIntValue = String.format("%,d", priceValue.value.toInt()).replace(",", " ")

    return String.format("%s, %s", textFormattedIntValue, doitText)
}
