package com.bet_planet.android.data.utils.locale

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import com.bet_planet.android.domain.utils.CommonStorage
import java.util.*

object LocaleUtil {

    private lateinit var commonStorage: CommonStorage

    fun getCurrentLocale(): Locale {
        val defaultLocale = Locale.getDefault()
        for (locale in LocaleListHolder.localeList) {
            if (locale.language == defaultLocale.language) {
                return locale
            }
        }
        return LocaleListHolder.defaultLang
    }

    fun onAttach(context: Context?): Context? {
        context?.let {
            if (!this::commonStorage.isInitialized) {
                commonStorage = CommonStorage(context)
            }
            val lang = commonStorage.getString(CommonStorage.PREF_SELECTED_LANGUAGE)
            getSavedLocale(lang)?.let { newLocale ->
                Locale.setDefault(newLocale)
                val config = Configuration(it.resources.configuration)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    val localeList = LocaleList(newLocale)
                    config.setLocales(localeList)
                    LocaleList.setDefault(localeList)
                } else {
                    config.setLocale(newLocale)
                }
                return it.createConfigurationContext(config)
            }
        }
        return context
    }

    fun getSavedLocale(language: String?): Locale? {
        if (!language.isNullOrEmpty()) {
            for (locale in LocaleListHolder.localeList) {
                if (locale.language == language) {
                    return locale
                }
            }
        }
        return null
    }
}
