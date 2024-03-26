package com.gcolina.recreativappcangreesl.detail.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.gcolina.recreativappcangreesl.databinding.EarningDialogBinding

class EarnningDialog(
    private val onClickListener: (net: String, total: String) -> Unit
) : DialogFragment() {
    private lateinit var binding: EarningDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = EarningDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.btnSaveEarnings.setOnClickListener {
            val net = binding.etNetEarnings.text.toString()
            val total = binding.etTotalEarnings.text.toString()
            onClickListener.invoke(net, total)
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}
