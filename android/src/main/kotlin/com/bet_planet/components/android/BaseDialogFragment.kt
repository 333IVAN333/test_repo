package com.bet_planet.components.android

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatDialogFragment

abstract class BaseDialogFragment : AppCompatDialogFragment() {

    companion object {
        private const val DEFAULT_DIM_AMOUNT = 0.8f
    }

    override fun onResume() {
        if (dialog != null && dialog!!.window != null) {
            val displayMetrics = DisplayMetrics()
            requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
            dialog!!.window!!.setLayout(
                    getWidthParams(displayMetrics),
                    getHeightParams(displayMetrics))
        }
        super.onResume()
    }

    protected fun getHeightParams(displayMetrics: DisplayMetrics?): Int {
        return ViewGroup.LayoutParams.WRAP_CONTENT
    }

    protected fun getWidthParams(displayMetrics: DisplayMetrics): Int {
        return (displayMetrics.widthPixels * 0.88).toInt()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutRes, container, false)
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
            val dimAmount = dimAmount
            if (dimAmount != null) {
                dialog!!.window!!.setDimAmount(dimAmount)
            }
        }
        return view
    }

    protected abstract val layoutRes: Int
    val dimAmount: Float?
        get() = DEFAULT_DIM_AMOUNT

}
