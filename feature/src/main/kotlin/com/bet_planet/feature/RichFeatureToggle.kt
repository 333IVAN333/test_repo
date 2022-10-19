package com.bet_planet.feature

data class RichFeatureToggle<T>(
    override val value: T,
    override val isToggleOn: Boolean,
    override val tag: String
) : RichFeatureTogglable<T>
