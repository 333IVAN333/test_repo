package com.bet_planet.android.domain.utils

import android.text.TextUtils
import android.util.Patterns

class CheckUtil {
    companion object {

        fun validPassword(password: String): Boolean {
            val regex = """^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[\W]).{4,}$""".toRegex();
            return regex.containsMatchIn(password)
        }

        fun isValidEmail(target: CharSequence): Boolean {
            return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }
}
