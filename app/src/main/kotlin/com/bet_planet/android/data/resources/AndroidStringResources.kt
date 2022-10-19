package com.bet_planet.android.data.resources

import android.content.Context
import com.bet_planet.android.domain.resources.StringResources
import javax.inject.Inject

internal class AndroidStringResources @Inject constructor(
    private val context: Context
) : StringResources {

    companion object {
        const val GROWN_STRING = 1
        const val CHILD_STRING = 0
    }

    private val resources = context.resources

    override fun getString(id: Int): String = context.getString(id)

    override fun getString(id: Int, vararg args: Any): String = context.getString(id, *args)

    override fun getVariableString(id: Int): String = ""
}
