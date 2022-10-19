package com.bet_planet.feature

interface FeatureToggleList : Iterable<FeatureTogglable> {

    val isLoaded: Boolean

    val coreToggle: FeatureTogglable

    fun count(): Int = toList().size

    override fun iterator(): Iterator<FeatureTogglable> = object : Iterator<FeatureTogglable> {
        private val iterator = listOf(
            coreToggle
        ).iterator()

        override fun hasNext(): Boolean = iterator.hasNext()

        override fun next(): FeatureTogglable = iterator.next()
    }
}
