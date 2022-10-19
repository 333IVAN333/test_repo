package com.bet_planet.android.presentation.signInFlow.password

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProviders
import com.bet_planet.android.R
import com.bet_planet.android.domain.registration.password.PasswordAction
import com.bet_planet.android.domain.registration.password.PasswordArguments
import com.bet_planet.android.domain.registration.password.PasswordState
import com.bet_planet.android.domain.registration.password.RestoreAction
import com.bet_planet.android.domain.utils.PhoneFormat
import com.bet_planet.components.presentation.MviViewModel
import com.bet_planet.dagger.DaggerFragment
import com.bet_planet.dagger.viewmodel.ComponentBuilder
import com.bet_planet.dagger.viewmodel.DaggerFragmentViewModelFactory
import com.bet_planet.dagger.viewmodel.HasViewModelSubComponentBuilders
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_enter_password.*

enum class PasswordMode {
    MODE_ENTER,
    MODE_CREATE_NEW
}

class PasswordFragment :
        DaggerFragment<PasswordAction, PasswordState, PasswordParcelable, PasswordViewModel>(),
        HasViewModelSubComponentBuilders {

    companion object {
        private const val ARGS_DATA = "ARGS_DATA"

        fun newInstance(args: PasswordArguments) = PasswordFragment().apply {
            arguments = bundleOf(
                    ARGS_DATA to args
            )
        }
    }

    override val layoutResId: Int = R.layout.fragment_enter_password
    override val actions: Observable<PasswordAction> = PublishRelay.create()

    private var currentMode: PasswordMode = PasswordMode.MODE_ENTER

    override fun getViewModelSubComponentBuilder(viewModelClass: Class<out MviViewModel<*, *>>): ComponentBuilder {
        val model = viewModel ?: throw IllegalStateException("View model is not build yet")
        return model.viewModelComponentFactory.get(viewModelClass)
    }

    override fun restoredAction(state: PasswordState): PasswordAction = RestoreAction(state)

    override fun buildViewModel(restoredState: PasswordState?): PasswordViewModel {
        val args = arguments?.getParcelable<PasswordArguments>(ARGS_DATA)!!
        currentMode = args.mode
        val factory = DaggerFragmentViewModelFactory<PasswordState, PasswordViewModel>(
                this, args, restoredState
        )

        return ViewModelProviders.of(this, factory).get(PasswordViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()

        actionBtn.setOnClickListener {
            if (enter_password_input.text.isNotEmpty()) {
                viewModel?.actionClick(currentMode, enter_password_input.text.toString())
            } else {
                viewModel?.showEmptyPasswordError()
            }
        }
        restorePasswordBtn.setOnClickListener {
            viewModel?.restorePasswordClick()
        }
        enter_password_input.doAfterTextChanged {
            viewModel?.clearPasswordError()
        }
    }

    override fun render(state: PasswordParcelable) {
        when (currentMode) {
            PasswordMode.MODE_ENTER -> {
                text_password_header.setText(R.string.input_pass_password)
                restorePasswordBtn.isVisible = true
                actionBtn.text = getString(R.string.sign_in)
                passwordToolbar.setBetTitle(R.string.sign_in)
                textHeader.text = getString(R.string.input_pass_header_text,
                        PhoneFormat.getFormattedPhone("+${state.country.code}${state.phone}"))

            }
            PasswordMode.MODE_CREATE_NEW -> {
                text_password_header.setText(R.string.input_pass_password_new)
                restorePasswordBtn.isInvisible = true
                actionBtn.text = getString(R.string.confirm)
                passwordToolbar.setBetTitle(R.string.password_recovery)
                textHeader.text = getString(R.string.create_a_new_password)
            }
        }
        enter_password_layout.error = state.errorPassword
        loadingProgressBar.isInvisible = !state.loading
    }

    override fun onOpenKeyboard() {
        super.onOpenKeyboard()
        actionBtn.isVisible = false
        restorePasswordBtn.isVisible = false
    }

    override fun onHideKeyboard() {
        super.onHideKeyboard()
        actionBtn.isVisible = true
        restorePasswordBtn.isInvisible = currentMode == PasswordMode.MODE_CREATE_NEW
    }

    private fun initToolbar() {
        passwordToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_item_close -> {
                    viewModel?.closeFlow()
                    true
                }
                else -> false
            }
        }
        passwordToolbar.setNavigationOnClickListener {
            viewModel?.backAction()
        }
    }

    private fun showMessage(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}
