package com.bet_planet.feature

data class FeatureToggle(
    override val isToggleOn: Boolean,
    override val tag: String
) : FeatureTogglable

fun FeatureTogglable.mutate(isToggleOn: Boolean): FeatureTogglable =
    FeatureToggle(isToggleOn, tag)
