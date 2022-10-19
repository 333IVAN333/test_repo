package com.bet_planet.components.android

import com.bet_planet.android.domain.homePage.HomePageAction
import com.bet_planet.android.domain.homePage.HomePageState
import com.bet_planet.android.presentation.homePage.HomePageParcelable
import com.bet_planet.android.presentation.homePage.HomePageViewModel
import com.bet_planet.components.domain.Mapper

class HomePageFragmentFactory {
    fun inject(
            fragment: BaseFragment<HomePageAction, HomePageState, HomePageParcelable, HomePageViewModel>,
            stateMapper: Mapper<HomePageState, HomePageParcelable>,
            parcelMapper: Mapper<HomePageParcelable, HomePageState>
    ) {
        fragment.parcelMapper = parcelMapper
        fragment.stateMapper = stateMapper
    }
}