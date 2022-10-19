package com.bet_planet.android.data.utils

import android.os.Build
import android.text.Html
import java.lang.Exception

object StringFormatter {

    fun getFormattedHtml(sourceHtml: String?): CharSequence {
        sourceHtml?.let {
            val spannedHtml = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(sourceHtml, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(sourceHtml)
            }
            return spannedHtml.trim()
        } ?: return ""
    }

    fun replaceSpecifiers(sourceText: String, originalKey: String, originalValue: String): String {
        var resultText = sourceText

        val key = "{${originalKey}}"
        resultText = resultText.replace(
                key,
                originalValue,
                true
        )
        val key2Variant = "{+${originalKey}}"
        if (resultText.contains(key2Variant, true)) {
            val number = tryGetNumber(originalValue)
            val paramText = number?.let {
                if (it > 0) "+${it}" else "$it"
            } ?: originalValue
            resultText = resultText.replace(
                    key2Variant,
                    paramText,
                    true
            )
        }

        val key3Variant = "{-${originalKey}}"
        if (resultText.contains(key3Variant, true)) {
            val number = tryGetNumber(originalValue)
            val paramText = number?.let { "${-1f * it}" } ?: originalValue
            resultText = resultText.replace(
                    key3Variant,
                    paramText,
                    true
            )
        }

        val key4Variant = "{!${originalKey}}"
        resultText = resultText.replace(
                key4Variant,
                originalValue,
                true
        )

        return resultText
    }

    private fun tryGetNumber(originalValue: String): Float? {
        try {
            return originalValue.toFloat()
        } catch (e: Exception) {
            //nothing
        }
        return null
    }
}
