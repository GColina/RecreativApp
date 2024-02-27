package com.gcolina.recreativappcangreesl.home.RecyclerViews.rvMachines

import com.gcolina.recreativappcangreesl.home.RecyclerViews.rvCategories.TypesCategoriesMachines

data class Machine(
    val BarName: String,
    // val ImageMachine: ImageView,
    val nameOwner: String,
    val PhoneOwner: String,
    val category: TypesCategoriesMachines,
    var isSelected: Boolean = false,
)
