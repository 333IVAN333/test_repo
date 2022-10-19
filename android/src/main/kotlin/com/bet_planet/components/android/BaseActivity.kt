package com.bet_planet.components.android

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.bet_planet.components.domain.Action
import com.bet_planet.components.domain.Mapper
import com.bet_planet.components.domain.State
import com.bet_planet.components.presentation.MviView
import com.bet_planet.components.presentation.MviViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseActivity<A : Action, S : State, P : Parcelable, M : MviViewModel<A, S>> : AppCompatActivity(),
    MviView<A, S> {

    companion object {
        const val VIEW_STATE = "view_state"
    }

    protected abstract val layoutResId: Int

    @Inject
    lateinit var stateMapper: Mapper<S, P>
    @Inject
    lateinit var parcelMapper: Mapper<P, S>

    protected var viewModel: M? = null

    private var state: P? = null
    private var disposable: Disposable? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        inject()

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

        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        initViews(savedInstanceState)
        dispose()
        disposable = viewModel?.bind(this)
    }

    abstract fun restoredAction(state: S): A

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        state?.let {
            outState.putParcelable(VIEW_STATE, it)
        }
    }

    @CallSuper
    override fun onDestroy() {
        dispose()
        super.onDestroy()
    }

    final override fun render(state: S) {
        val newState = stateMapper.map(state).blockingGet()
        this.state = newState
        render(newState)
    }

    protected open fun initViews(savedInstanceState: Bundle?) {
//      default
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
