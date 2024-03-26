package com.gcolina.recreativappcangreesl.componentsRv.rvEarnings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcolina.recreativappcangreesl.R
import com.gcolina.recreativappcangreesl.data.model.EarningsModel

class EarningAdapter() : RecyclerView.Adapter<EarningViewHolder>() {
    private var earnings: List<EarningsModel> = emptyList()
    fun updateItems(items: List<EarningsModel>) {
        earnings = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarningViewHolder {
        return EarningViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_earnings, parent, false)
        )
    }

    override fun getItemCount() = earnings.size

    override fun onBindViewHolder(holder: EarningViewHolder, position: Int) {
        holder.bind(earnings[position])
    }
}
