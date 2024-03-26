package com.gcolina.recreativappcangreesl.componentsRv.rvAlerts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcolina.recreativappcangreesl.R
import com.gcolina.recreativappcangreesl.data.model.AlertModel

class AlertsAdapter() : RecyclerView.Adapter<AlertViewHolder>() {
    private var alerts: List<AlertModel> = emptyList()

    fun upDateItems(items: List<AlertModel>) {
        alerts = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder {
        return AlertViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_alerts, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int) {
        holder.bind(alerts[position])
    }

    override fun getItemCount() = alerts.size
}
