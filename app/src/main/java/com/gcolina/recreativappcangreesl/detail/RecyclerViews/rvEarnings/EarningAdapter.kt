package com.gcolina.recreativappcangreesl.detail.RecyclerViews.rvEarnings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcolina.recreativappcangreesl.R

class EarningAdapter(private val earnings: List<Earning>) :
    RecyclerView.Adapter<EarningViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EarningViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_earnings, parent, false)
        return EarningViewHolder(view)
    }

    override fun getItemCount() = earnings.size

    override fun onBindViewHolder(
        holder: EarningViewHolder,
        position: Int,
    ) {
        holder.render(earnings[position])
    }
}
