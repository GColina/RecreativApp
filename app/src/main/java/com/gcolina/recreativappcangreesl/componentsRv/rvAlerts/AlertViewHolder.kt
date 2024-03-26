package com.gcolina.recreativappcangreesl.componentsRv.rvAlerts

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gcolina.recreativappcangreesl.data.model.AlertModel
import com.gcolina.recreativappcangreesl.databinding.ItemAlertsBinding

class AlertViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemAlertsBinding.bind(view)

    fun bind(alertModel: AlertModel) {
        binding.tvMessageAlert.text = alertModel.message
        binding.tvItemUser.text = alertModel.user
        binding.tvItemDate.text = alertModel.date
    }
}
