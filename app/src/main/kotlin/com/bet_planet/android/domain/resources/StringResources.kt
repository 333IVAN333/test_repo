package com.bet_planet.android.domain.resources

interface StringResources {

    fun getString(id: Int): String

    fun getString(id: Int, vararg args: Any): String

    fun getVariableString(id: Int): String
}
