package com.bet_planet.android.presentation.signInFlow.reset_password

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProviders
import com.bet_planet.android.R
import com.bet_planet.android.domain.registration.reset_password.ResetPasswordAction
import com.bet_planet.android.domain.registration.reset_password.ResetPasswordArguments
import com.bet_planet.android.domain.registration.reset_password.ResetPasswordState
import com.bet_planet.android.domain.registration.reset_password.RestoreAction
import com.bet_planet.android.domain.resources.StringResources
import com.bet_planet.android.domain.utils.PhoneFormat
import com.bet_planet.android.presentation.utils.SpanUtil
import com.bet_planet.dagger.DaggerFragment
import com.bet_planet.dagger.viewmodel.DaggerFragmentViewModelFactory
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_reset_password_code.*
import javax.inject.Inject


enum class VerifySmsCodeMode {
    SMS_RECOVERY_PASSWORD
}

class ResetPasswordFragment :
        DaggerFragment<ResetPasswordAction, ResetPasswordState, ResetPasswordParcelable, ResetPasswordViewModel>() {

    companion object {
        private const val ARGS_DATA = "ARGS_DATA"
        fun newInstance(args: ResetPasswordArguments): ResetPasswordFragment =
                ResetPasswordFragment().apply {
                    arguments = bundleOf(
                            ARGS_DATA to args
                    )
                }
    }

    override val actions: Observable<ResetPasswordAction> = PublishRelay.create()
    override val layoutResId: Int = R.layout.fragment_reset_password_code

    @Inject
    internal lateinit var stringRes: StringResources

    private val handler = Handler(Looper.getMainLooper())

    private var isClearErrorPosted: Boolean = false
    private var textWatcher: TextWatcher? = null

    private lateinit var phone: String
    private lateinit var mode: VerifySmsCodeMode


    override fun buildViewModel(restoredState: ResetPasswordState?): ResetPasswordViewModel {
        val args = arguments?.getParcelable<ResetPasswordArguments>(ARGS_DATA)!!
        phone = args.phone
        mode = args.checkType
        val factory =
                DaggerFragmentViewModelFactory<ResetPasswordState, ResetPasswordViewModel>(
                        this, args, restoredState
                )
        return ViewModelProviders.of(this, factory).get(ResetPasswordViewModel::class.java)
    }

    override fun restoredAction(state: ResetPasswordState): ResetPasswordAction = RestoreAction(state)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enterSmsCodeInput.setOnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel?.nextClick(enterSmsCodeInput.text.toString(), enter_password_input.text.toString())
            }
            false
        }
        enterSmsCodeInput.doAfterTextChanged {
            if (it?.isNotEmpty() == true) {
                viewModel?.resetError()
            }
        }
        enter_password_input.doAfterTextChanged {
            if (it?.isNotEmpty() == true) {
                viewModel?.resetError()
            }
        }

        nextBtn.setOnClickListener {
            viewModel?.nextClick(enterSmsCodeInput.text.toString(), enter_password_input.text.toString())
        }

        requestCodeBtn.setOnClickListener {
            viewModel?.requestResetPasswordCode()
        }

        initToolbar()

        viewModel?.restartTimer()
    }

    override fun render(state: ResetPasswordParcelable) {

        verifyCodeToolbar.setBetTitle(R.string.password_recovery)
        nextBtn.setText(R.string.confirm)

        text_header.text = getString(R.string.reset_password_header_text,
                PhoneFormat.getFormattedPhone("+${state.country.code}${state.phone}"))

        requestCodeBtn.isInvisible = state.loadingRequestCode || keyboardShown
        enterSmsCodeLayout.error =
                if (state.codeFormatError) getString(R.string.sms_code_incorrect)
                else ""

        enter_password_layout.error = state.errorPassword

        requestCodeBtn.isEnabled = state.secondsToRetry <= 0
        if (state.secondsToRetry > 0) {
            val secondsStr = state.secondsToRetry.toString()
            val finalString = getString(R.string.verify_sms_request_code_again_after, secondsStr)
            requestCodeBtn.text = SpanUtil.createColorSpan(requireContext(), finalString, secondsStr, R.color.color_blue_primary)
        } else {
            requestCodeBtn.text = getString(R.string.verify_sms_request_code_again)
        }

        if (loadingProgressBar.isVisible && !state.loadingRegister) {
            enterSmsCodeInput.text?.clear()
        }
        loadingProgressBar.isVisible = state.loadingRegister || state.loadingRequestCode
    }

    override fun onDestroyView() {
        textWatcher = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        isClearErrorPosted = false
        super.onDestroy()
    }

    private fun initToolbar() {
        verifyCodeToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_item_close -> {
                    viewModel?.closeFlow()
                    true
                }
                else -> false
            }
        }
        verifyCodeToolbar.setNavigationOnClickListener {
            viewModel?.back()
        }
    }

    override fun onOpenKeyboard() {
        super.onOpenKeyboard()
        nextBtn.isVisible = false
        requestCodeBtn.isVisible = false
    }

    override fun onHideKeyboard() {
        super.onHideKeyboard()
        val loadingRequestCode = viewModel?.model()?.state?.blockingFirst()?.loadingRequestCode
                ?: false
        requestCodeBtn.isInvisible = loadingRequestCode

        val loadingFirst = viewModel?.model()?.state?.blockingFirst()?.loadingFirst ?: false
        nextBtn.isVisible = !loadingFirst
    }
}
