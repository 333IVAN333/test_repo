package com.bet_planet.feature.data

import android.content.SharedPreferences
import com.bet_planet.feature.*
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import javax.inject.Inject

internal class SharedFeatureToggleSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : FeatureToggleSource {

    override fun featureToggleList(): Single<List<FeatureToggleList>> = Single.fromCallable {
        listOf(
                StaticFeatureToggleList(coreToggle = FeatureToggle(true, tag = FEATURE_ADD_TAB_PROFILE)),
                StaticFeatureToggleList(coreToggle = FeatureToggle(true, tag = FEATURE_CHECK_NEW_VERSION)),
                StaticFeatureToggleList(coreToggle = FeatureToggle(true, tag = FEATURE_USE_LOCK_SCREEN_PARAMETER))
        ).map {
            it.copy(
                    isLoaded = true,
                    coreToggle = it.coreToggle.mutate(
                            sharedPreferences.getBoolean(
                                    it.coreToggle.tag,
                                    it.coreToggle.isToggleOn
                            )
                    )
            )
        }.toList()
    }
}
