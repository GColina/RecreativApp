package com.gcolina.recreativappcangreesl.home.components.rvAssets

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gcolina.recreativappcangreesl.data.model.AssetModel
import com.gcolina.recreativappcangreesl.databinding.ItemAssetsBinding

class AssetViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemAssetsBinding.bind(view)

    fun bind(assetModel: AssetModel, onItemSelected: (AssetModel) -> Unit) {
        binding.tvstoreName.text = assetModel.storeName
        binding.tvOwnerName.text = assetModel.ownerName
        binding.tvOwnerPhone.text = assetModel.ownerPhoneNumber
        binding.root.setOnClickListener { onItemSelected(assetModel) }
    }
}
