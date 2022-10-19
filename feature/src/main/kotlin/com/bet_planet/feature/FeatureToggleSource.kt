package com.bet_planet.feature

import io.reactivex.Single

interface FeatureToggleSource {

    fun featureToggleList(): Single<List<FeatureToggleList>>
}
