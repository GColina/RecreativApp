package com.gcolina.recreativappcangreesl.detail.RecyclerViews.rvAlerts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcolina.recreativappcangreesl.R

class AlertAdapter(private val alerts: List<Alerts>) : RecyclerView.Adapter<AlertsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AlertsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alerts, parent, false)
        return AlertsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: AlertsViewHolder,
        position: Int,
    ) {
        holder.render(alerts[position])
    }

    override fun getItemCount() = alerts.size
}
