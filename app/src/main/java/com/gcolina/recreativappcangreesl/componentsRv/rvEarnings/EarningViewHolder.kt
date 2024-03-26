package com.gcolina.recreativappcangreesl.componentsRv.rvEarnings

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gcolina.recreativappcangreesl.data.model.EarningsModel
import com.gcolina.recreativappcangreesl.databinding.ItemEarningsBinding

class EarningViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemEarningsBinding.bind(view)

    fun bind(earningModel: EarningsModel) {
        binding.tvDateEarning.text = earningModel.date
        binding.tvNetEarnings.text = earningModel.netEarnings
        binding.tvTotalEarnings.text = earningModel.totalEarnings
    }
}
