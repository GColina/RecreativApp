package com.gcolina.recreativappcangreesl.detail.RecyclerViews.rvEarnings

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gcolina.recreativappcangreesl.databinding.ItemEarningsBinding

class EarningViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemEarningsBinding.bind(view)

    fun render(earning: Earning) {
        binding.tvDateEarning.text = earning.dateEarning
        binding.tvNetEarnings.text = earning.netEarnings
        binding.tvTotalEarnings.text = earning.totalEarnings
    }
}
