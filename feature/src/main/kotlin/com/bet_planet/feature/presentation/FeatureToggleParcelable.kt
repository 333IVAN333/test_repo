package com.bet_planet.feature.presentation

import android.os.Parcelable
import com.bet_planet.feature.data.StaticFeatureToggleList
import com.bet_planet.feature.FeatureTogglable
import com.bet_planet.feature.FeatureToggle
import com.bet_planet.feature.FeatureToggleList
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeatureToggleParcelable(
    override val isToggleOn: Boolean,
    override val tag: String
) : Parcelable, FeatureTogglable

fun FeatureToggleParcelable.toDomain(): FeatureTogglable =
    FeatureToggle(
        isToggleOn = isToggleOn,
        tag = tag
    )
fun FeatureTogglable.toParcelable(): FeatureToggleParcelable =
    FeatureToggleParcelable(
        isToggleOn = isToggleOn,
        tag = tag
    )

@Parcelize
data class FeatureToggleListParcelable(
    override val isLoaded: Boolean,
    override val coreToggle: FeatureToggleParcelable
) : Parcelable, FeatureToggleList

fun FeatureToggleListParcelable.toDomain(): FeatureToggleList =
    StaticFeatureToggleList(
        isLoaded = isLoaded,
        coreToggle = coreToggle.toDomain()
    )

fun FeatureToggleList.toParcelable(): FeatureToggleListParcelable =
    FeatureToggleListParcelable(
        isLoaded = isLoaded,
        coreToggle = coreToggle.toParcelable()
    )
