package com.gcolina.recreativappcangreesl.home.components.rvAssets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcolina.recreativappcangreesl.R
import com.gcolina.recreativappcangreesl.data.model.AssetModel

class AssetsAdapter(private val onItemSelected: (AssetModel) -> Unit) :
    RecyclerView.Adapter<AssetViewHolder>() {
    private var assets: List<AssetModel> = emptyList()
    fun updateItems(items: List<AssetModel>) {
        assets = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        return AssetViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_assets, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        holder.bind(assets[position], onItemSelected)
    }

    override fun getItemCount() = assets.size
}
