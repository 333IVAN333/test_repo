package com.bet_planet.components.android

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.CallSuper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.bet_planet.components.domain.Action
import com.bet_planet.components.domain.Mapper
import com.bet_planet.components.domain.State
import com.bet_planet.components.presentation.MviView
import com.bet_planet.components.presentation.MviViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseBottomSheetDialogFragment<A : Action, S : State, P : Parcelable,
    M : MviViewModel<A, S>> : BottomSheetDialogFragment(),
    MviView<A, S> {

    companion object {
        const val VIEW_STATE = "view_state"
        private const val KEYBOARD_SHOW_HIDE_DELAY_IN_MS: Long = 50
    }

    protected var viewModel: M? = null

    private var state: P? = null
    private var disposable: Disposable? = null

    @Inject
    lateinit var stateMapper: Mapper<S, P>
    @Inject
    lateinit var parcelMapper: Mapper<P, S>

    @CallSuper
    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var restoredState: S? = null
        savedInstanceState?.let {
            val localState = it.getParcelable<P>(VIEW_STATE)
            state = localState
            if (localState != null) {
                restoredState = parcelMapper.map(localState).blockingGet()
            }
        }

        viewModel = buildViewModel(restoredState)
        restoredState?.let {
            viewModel?.model()?.actions?.accept(
                restoredAction(
                    state = it
                )
            )
        }
    }

    abstract fun restoredAction(state: S): A

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dispose()
        disposable = viewModel?.bind(this)
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        state?.let {
            outState.putParcelable(VIEW_STATE, it)
        }
    }

    @CallSuper
    override fun onDestroyView() {
        dispose()
        super.onDestroyView()
    }

    final override fun render(state: S) {
        val newState = stateMapper.map(state).blockingGet()
        this.state = newState
        render(newState)
    }

    protected fun showKeyBoard(view: View) {
        val inputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        view.requestFocus()
        Handler().postDelayed(
            { inputMethodManager.showSoftInput(view, 0) },
            KEYBOARD_SHOW_HIDE_DELAY_IN_MS
        )
    }

    protected fun hideKeyBoard(view: View) {
        val inputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        Handler().postDelayed(
            { inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0) },
            KEYBOARD_SHOW_HIDE_DELAY_IN_MS
        )
    }

    protected abstract fun inject()

    protected abstract fun buildViewModel(restoredState: S?): M

    protected abstract fun render(state: P)

    private fun dispose() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }
}
