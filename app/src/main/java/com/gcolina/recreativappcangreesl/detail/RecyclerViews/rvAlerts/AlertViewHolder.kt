package com.gcolina.recreativappcangreesl.detail.RecyclerViews.rvAlerts

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gcolina.recreativappcangreesl.databinding.ItemAlertsBinding

class AlertsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemAlertsBinding.bind(view)

    fun render(alerts: Alerts) {
        binding.tvItemAlert.text = alerts.ItemAlert
        binding.tvItemUser.text = alerts.ItemUser
        binding.tvItemDate.text = alerts.ItemDate
    }
}
