package com.bet_planet.android.presentation.signInFlow.phone

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.core.view.doOnNextLayout
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProviders
import com.bet_planet.android.R
import com.bet_planet.android.domain.registration.phone.EnterPhoneNumberAction
import com.bet_planet.android.domain.registration.phone.EnterPhoneNumberState
import com.bet_planet.android.domain.registration.phone.RestoreAction
import com.bet_planet.android.domain.resources.StringResources
import com.bet_planet.android.domain.utils.PhoneFormat
import com.bet_planet.dagger.DaggerFragment
import com.bet_planet.dagger.viewmodel.DaggerFragmentViewModelFactory
import com.jakewharton.rxrelay2.PublishRelay
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import javax.inject.Inject


class EnterPhoneNumberFragment : DaggerFragment<EnterPhoneNumberAction, EnterPhoneNumberState,
        EnterPhoneNumberParcelable, EnterPhoneNumberViewModel>() {

    companion object {
        fun newInstance(): EnterPhoneNumberFragment = EnterPhoneNumberFragment()
    }

    override val actions: Observable<EnterPhoneNumberAction> = PublishRelay.create()
    override val layoutResId: Int = R.layout.fragment_enter_phone_number
    private var textWatcher: TextWatcher? = null

    private var normalTitleColor: Int = 0
    private var errorTitleColor: Int = 0

    @Inject
    lateinit var stringRes: StringResources

    override fun buildViewModel(restoredState: EnterPhoneNumberState?): EnterPhoneNumberViewModel {
        val factory =
                DaggerFragmentViewModelFactory<EnterPhoneNumberState, EnterPhoneNumberViewModel>(
                        this, null, restoredState
                )
        return ViewModelProviders.of(this, factory).get(EnterPhoneNumberViewModel::class.java)
    }

    override fun restoredAction(state: EnterPhoneNumberState): EnterPhoneNumberAction =
            RestoreAction(state)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        normalTitleColor = text_country_header.currentTextColor
        errorTitleColor = ContextCompat.getColor(requireContext(), R.color.color_error)

        enter_phone_toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_item_close -> {
                    viewModel?.closeFlow()
                    true
                }
                else -> false
            }
        }

        select_country_click_view.setOnClickListener {
            //viewModel?.openCountryChooser()
        }

        next_btn.setOnClickListener {
            startCheckPhone()
        }
        enter_phone_input.setOnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                startCheckPhone()
            }
            false
        }
        enter_phone_input.doAfterTextChanged {
            viewModel?.clearPhoneError()
        }
        viewModel?.observeSelectedCountry(this)

        val window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.statusBarColor)

        contactUsBtn.setOnClickListener { viewModel?.navigateToSupport() }
    }

    override fun render(state: EnterPhoneNumberParcelable) {

        next_btn.isVisible = !state.loading
        nextProgressBar.isVisible = state.loading

        select_country_layout.error = state.errorCountry
        enter_phone_layout.error = state.errorPhone

        text_country_header.setTextColor(if (state.errorCountry.isNullOrEmpty()) normalTitleColor else errorTitleColor)
        text_phone_header.setTextColor(if (state.errorCountry.isNullOrEmpty()) normalTitleColor else errorTitleColor)

        state.country?.let {

            select_country_input.setText(it.name)

            val newPrefix = String.format("+%s", it.code)
            val isChangeCountry = newPrefix != phone_prefix.text.toString()
            phone_prefix.text = newPrefix
            enter_phone_input.isEnabled = true
            phone_prefix.doOnNextLayout { prefix ->
                enter_phone_input.updatePadding(left = prefix.measuredWidth)
            }
            enter_phone_input.removeTextChangedListener(textWatcher)
            if (it.phoneFormat.isNullOrEmpty()) {
                enter_phone_input.hint = getString(R.string.enter_your_phone)
                textWatcher = object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        val prefix = phone_prefix.text.toString()
                        val formattedPhone = PhoneFormat
                                .getFormattedPhone(prefix + s.toString())
                                .replace(prefix, "")
                        if (s.toString() != formattedPhone && formattedPhone.isNotEmpty()) {
                            enter_phone_input.setText(formattedPhone)
                            enter_phone_input.setSelection(enter_phone_input.length())
                        }
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    }

                }
                enter_phone_input.addTextChangedListener(textWatcher)
            } else {
                textWatcher = MaskedTextChangedListener(
                        it.phoneFormat,
                        emptyList(),
                        AffinityCalculationStrategy.PREFIX,
                        false,
                        enter_phone_input,
                        null,
                        null
                )

                enter_phone_input.addTextChangedListener(textWatcher)
                enter_phone_input.onFocusChangeListener = textWatcher as? MaskedTextChangedListener
                enter_phone_input.hint = (textWatcher as? MaskedTextChangedListener)?.placeholder()?.replace("0", "_")

            }
            if (enter_phone_input.text.isEmpty() && state.errorPhone.isNullOrEmpty() || isChangeCountry) {
                enter_phone_input.setText(state.phone)
            }
        } ?: run {
            select_country_input.text = null
            phone_prefix.text = null
            enter_phone_input.text = null
            select_country_input.text = null
            enter_phone_input.isEnabled = false
            enter_phone_input.hint = getString(R.string.enter_your_phone)
        }
    }

    override fun onOpenKeyboard() {
        super.onOpenKeyboard()
        next_btn.isVisible = false
    }

    override fun onHideKeyboard() {
        super.onHideKeyboard()
        val loading = viewModel?.model()?.state?.blockingFirst()?.loading ?: false
        next_btn.isVisible = !loading
    }

    private fun startCheckPhone() {
        val phone = PhoneFormat.stripExceptNumbers(enter_phone_input.text.toString())
        viewModel?.sendPhone(phone)
    }
}
