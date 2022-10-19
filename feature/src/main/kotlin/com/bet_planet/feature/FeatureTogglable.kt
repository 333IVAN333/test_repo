package com.bet_planet.feature

/**
 * Интерфейс для переключения состояния фичи
 */
interface FeatureTogglable {

    /**
     * Указывает состояние фичи true - включена, false - выключена
     */
    val isToggleOn: Boolean

    /**
     * Уникальный тэг фичи
     */
    val tag: String
}

val FeatureTogglable.isToggleOff: Boolean
    get() = !isToggleOn
