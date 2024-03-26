package com.gcolina.recreativappcangreesl.detail.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.gcolina.recreativappcangreesl.databinding.AlertDialogBinding

class AlertDialog(
    private val onClickListener: (alert: String) -> Unit
) : DialogFragment() {
    private lateinit var binding: AlertDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = AlertDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.btnSaveAlerts.setOnClickListener {
            val alert = binding.etAlert.text.toString()
            onClickListener.invoke(alert)
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}
