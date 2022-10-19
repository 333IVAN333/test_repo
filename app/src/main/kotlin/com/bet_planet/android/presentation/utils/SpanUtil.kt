package com.bet_planet.android.presentation.utils

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.text.*
import android.text.Annotation
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.MetricAffectingSpan
import android.text.style.TextAppearanceSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bet_planet.android.R

object SpanUtil {

    fun createColorSpan(context: Context, text: String, colorizedPart: String, @ColorRes colorRes: Int): Spannable {
        val spannable = SpannableString(text)
        val startIndex = text.indexOf(colorizedPart)
        if (startIndex != -1) {
            val colorSpan = ForegroundColorSpan(ContextCompat.getColor(context, colorRes))
            spannable.setSpan(colorSpan, startIndex, startIndex + colorizedPart.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return spannable
    }

    fun setTextWithSearch(v: TextView, text: String, searchText: String?) {
        if (!searchText.isNullOrEmpty() && text.contains(searchText, true)) {
            v.text = getSearchSpanText(v.context, text, searchText, v.currentTextColor)
        } else {
            v.text = text
        }
    }

    private fun getSearchSpanText(context: Context, text: String, searchText: String, otherColorText: Int): Spannable? {
        val spanString = SpannableString(text)
        val index = text.indexOf(searchText, ignoreCase = true)
        if (index >= 0) {
            spanString.setSpan(ForegroundColorSpan(getAlphaColor(otherColorText)), 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            //applyFont(context, spanString, index, index + searchText.length, R.font.montserrat_bold)
            spanString.setSpan(ForegroundColorSpan(otherColorText), index, index + searchText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return spanString
    }

    private fun getAlphaColor(color: Int): Int {
        return color and 0x00FFFFFF or 0x80000000.toInt()
    }

    fun setClickableDestOnText(
            context: Context,
            @StringRes textRes: Int,
            listener: (String) -> Unit
    ): SpannableStringBuilder? {
        val emptyText = context.getText(textRes) as SpannedString
        val ssb = SpannableStringBuilder(emptyText)
        val annotations = emptyText.getSpans(0, emptyText.length, Annotation::class.java)
        if (!annotations.isNullOrEmpty()) {
            val annotation = annotations[0]
            if (annotation.key == "dest") {
                val clickableSpan: ClickableSpan = object : ClickableSpan() {

                    override fun onClick(widget: View) {
                        widget.cancelPendingInputEvents()
                        listener.invoke(annotation.key)
                    }
                }
                ssb.setSpan(clickableSpan,
                        emptyText.getSpanStart(annotation),
                        emptyText.getSpanEnd(annotation),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
        return ssb
    }

    fun applyFontBold(context: Context, initText: Spannable, boldText: String) {
        val start = initText.indexOf(boldText, ignoreCase = true)
        if (start != -1) {
            val fontSpan = TypefaceSpan(ResourcesCompat.getFont(context, R.font.montserrat_bold))
            initText.setSpan(fontSpan, start, start + boldText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    fun applyFont(context: Context?, spannable: Spannable, start: Int, end: Int, @FontRes fontResId: Int) {
        spannable.setSpan(TypefaceSpan(ResourcesCompat.getFont(context!!, fontResId)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    private class TypefaceSpan internal constructor(typeface: Typeface?) : MetricAffectingSpan() {
        private val typeface: Typeface
        override fun updateDrawState(drawState: TextPaint) {
            apply(drawState)
        }

        override fun updateMeasureState(paint: TextPaint) {
            apply(paint)
        }

        private fun apply(paint: Paint) {
            val oldTypeface = paint.typeface
            val oldStyle = oldTypeface?.style ?: 0
            val fakeStyle = oldStyle and typeface.style.inv()
            if (fakeStyle and Typeface.BOLD != 0) {
                paint.isFakeBoldText = true
            }
            if (fakeStyle and Typeface.ITALIC != 0) {
                paint.textSkewX = -0.25f
            }
            paint.typeface = typeface
        }

        init {
            requireNotNull(typeface) { "typeface is null" }
            this.typeface = typeface
        }
    }

    fun getSpannableFromHtml(html: String?): Spanned? {
        return if (html == null) {
            // return an empty spannable if the html is null
            SpannableString("")
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions below android N
            // we are using this flag to give a consistent behaviour
            Html.fromHtml(html, Html.FROM_HTML_SEPARATOR_LINE_BREAK_LIST)
        } else {
            Html.fromHtml(html)
        }
    }

    fun getSpannableCompetitorName(context: Context, textValue: String, isActive: Boolean): SpannableString {
        val spannableText = SpannableString(textValue + if(isActive) " •" else "  ")
        spannableText.setSpan(
                TextAppearanceSpan(
                        context,
                        R.style.ActiveCompetitor
                ),
                (spannableText.length - 2),
                spannableText.length,
                SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableText
    }


    fun getSpannableScoreText(context: Context, textValue: String, isActiveCompetitor: Boolean): SpannableString {
        val spannableText = SpannableString((if(isActiveCompetitor) "• " else "  ") + textValue)
        spannableText.setSpan(
                TextAppearanceSpan(
                        context,
                        R.style.ActiveCompetitor
                ),
                0,
                2,
                SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableText
    }


}


