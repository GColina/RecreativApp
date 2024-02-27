package com.gcolina.recreativappcangreesl.home.RecyclerViews.rvCategories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcolina.recreativappcangreesl.R

class CategoriesAdapter(private val categories: List<TypesCategoriesMachines>) :
    RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_type_category, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(
        holder: CategoriesViewHolder,
        position: Int,
    ) {
        holder.render(categories[position])
    }
}
