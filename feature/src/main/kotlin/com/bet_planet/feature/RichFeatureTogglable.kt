package com.bet_planet.feature

/**
 * Расширение интерфейса [FeatureTogglable]
 * Такой переключатель может содержать дополнительную информацию о состоянии фичи
 */
interface RichFeatureTogglable<T> :
    FeatureTogglable {

    val value: T
}
