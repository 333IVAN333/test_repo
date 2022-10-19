package com.bet_planet.android.data.version

import com.bet_planet.android.data.api.CommonApi
import com.bet_planet.android.data.registration.dto.MinVersionResponse
import com.bet_planet.android.domain.homePage.VersionSource
import io.reactivex.Single
import javax.inject.Inject

internal class VersionDataSource @Inject constructor(
        private val api: CommonApi
) : VersionSource {

    override fun getMinVersion(): Single<MinVersionResponse> = api.getMinimumVersion()
}
