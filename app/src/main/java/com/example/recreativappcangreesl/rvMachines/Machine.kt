package com.example.recreativappcangreesl.rvMachines

import com.example.recreativappcangreesl.rvCategories.TypesCategoriesMachines

data class Machine(
    val BarName: String,
    /* val ImageMachine: ImageView,*/
    val nameOwner: String,
    val PhoneOwner: String,
    val category: TypesCategoriesMachines,
    var isSelected: Boolean = false
) {
}