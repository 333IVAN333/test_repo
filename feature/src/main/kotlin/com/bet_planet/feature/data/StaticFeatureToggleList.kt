package com.bet_planet.feature.data

import com.bet_planet.feature.FeatureTogglable
import com.bet_planet.feature.FeatureToggle
import com.bet_planet.feature.FeatureToggleList

private const val CORE_FEATURE = "core_feature"
const val FEATURE_ADD_TAB_PROFILE = "tab_profile_feature"
const val FEATURE_CHECK_NEW_VERSION = "check_new_version_feature"
const val FEATURE_USE_LOCK_SCREEN_PARAMETER = "use_lock_screen_feature"

data class StaticFeatureToggleList(
    override val isLoaded: Boolean = false,
    override val coreToggle: FeatureTogglable = FeatureToggle(
        true,
        CORE_FEATURE
    )
) : FeatureToggleList
