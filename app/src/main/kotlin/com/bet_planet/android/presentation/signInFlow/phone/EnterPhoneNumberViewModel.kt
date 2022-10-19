package com.bet_planet.android.presentation.signInFlow.phone

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.bet_planet.android.R
import com.bet_planet.android.domain.main.*
import com.bet_planet.android.domain.navigation.Screen
import com.bet_planet.android.domain.registration.phone.*
import com.bet_planet.android.domain.user.auth.UserAuthSource
import com.bet_planet.android.presentation.signInFlow.country.CountryListArgs
import com.bet_planet.android.presentation.utils.ChatScreenArguments
import com.bet_planet.components.android.BaseViewModel
import com.bet_planet.components.domain.Model
import com.bet_planet.components.domain.Schedulers
import com.bet_planet.components.domain.log.ApplicationLogger
import javax.inject.Inject

class EnterPhoneNumberViewModel @Inject constructor(
        schedulers: Schedulers,
        logger: ApplicationLogger,
        private val context: Context,
        model: Model<EnterPhoneNumberState, EnterPhoneNumberAction>,
        private val mainModel: Model<MainState, MainAction>,
        private val userAuthSource: UserAuthSource
) : BaseViewModel<EnterPhoneNumberAction, EnterPhoneNumberState>(schedulers, logger, model) {

    companion object {
        const val MAX_COUNT_DIGITS_IN_PHONE = 10
    }
    fun backAction() {
        mainModel.actions.accept(BackAction)
    }

    fun closeFlow() {
        mainModel.actions.accept(
                ClearChainAction(Screen.ENTER_PHONE_NUMBER)
        )
    }

    fun sendPhone(phone: String) {
        var phoneResult = phone
        if (phoneResult.isEmpty()) {
            model().actions.accept(ErrorPhoneAction(context.getString(R.string.required_field)))
            return
        }
        if (phone.length == MAX_COUNT_DIGITS_IN_PHONE && phone.startsWith("0")) {
            phoneResult = phone.substring(1)
        }
        model().state.blockingFirst().country?.let {
            model().actions.accept(CheckPhoneAction(phoneResult, it.code.toString()))
        }
    }

    fun openCountryChooser() {
        mainModel.actions.accept(
                NavigateAction(
                        Screen.COUNTRY_LIST,
                        CountryListArgs(model().state.blockingFirst().country?.getCorrectCountryId() ?: "")
                )
        )
    }

    fun observeSelectedCountry(lifecycleOwner: LifecycleOwner) {
        (mainModel as MainModel).selectedCountry.observe(lifecycleOwner, Observer {
            if (it != null) {
                model().actions.accept(SetSelectedCountry(it))
                mainModel.selectedCountry.value = null
            }
        })
    }

    fun clearPhoneError()  {
        model().actions.accept(ErrorPhoneAction(""))
    }

    fun navigateToSupport() {
        mainModel.actions.accept(
                NavigateAction(
                        screen = Screen.LIVE_CHAT,
                        data = ChatScreenArguments(
                                userAuthSource.isAuth(),
                                userAuthSource.userId
                        )
                )
        )
    }
}
