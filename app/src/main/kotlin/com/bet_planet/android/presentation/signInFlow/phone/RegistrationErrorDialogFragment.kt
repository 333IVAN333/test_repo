package com.bet_planet.android.presentation.signInFlow.phone

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment

private const val APP_PACKAGE_NAME = "ru.sbi.android"

class RegistrationErrorDialogFragment : DialogFragment() {

    private val argText get() = checkNotNull(arguments?.getString(ARG_TEXT))

    companion object {
        private const val ARG_TEXT = "ARG_TEXT"
        fun newInstance(text: String) = RegistrationErrorDialogFragment().apply {
            arguments = bundleOf(ARG_TEXT to text)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setMessage("") // required to get textView later
            .setPositiveButton(android.R.string.ok) { _, _ ->
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$APP_PACKAGE_NAME")
                        )
                    )
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=" +
                                APP_PACKAGE_NAME)
                        )
                    )
                }
            }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .create()
    }

    override fun onResume() {
        super.onResume()
        dialog?.let {
            val message = it.findViewById<TextView>(android.R.id.message)
            message.text = SpannableString(argText).apply { Linkify.addLinks(this, Linkify.WEB_URLS) }
            message.movementMethod = LinkMovementMethod.getInstance()
            it.findViewById<Button>(android.R.id.button1)?.requestFocus()
        }
    }
}
