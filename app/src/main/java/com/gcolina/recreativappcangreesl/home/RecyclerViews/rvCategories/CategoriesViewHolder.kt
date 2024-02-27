package com.gcolina.recreativappcangreesl.home.RecyclerViews.rvCategories

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gcolina.recreativappcangreesl.R

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)
    private val cvCategories: CardView = view.findViewById(R.id.cvCategories)

    fun render(typesCategoriesMachines: TypesCategoriesMachines) {
        tvCategoryName.text = "Ejemplo"

        when (typesCategoriesMachines) {
            TypesCategoriesMachines.Balls -> {
                tvCategoryName.text = "Pelotas"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.balls_category),
                )
            }

            TypesCategoriesMachines.Billiards -> {
                tvCategoryName.text = "Billares"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.billiards_category),
                )
            }

            TypesCategoriesMachines.Cars -> {
                tvCategoryName.text = "Coches"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.cars_category),
                )
            }

            TypesCategoriesMachines.ChewingGums -> {
                tvCategoryName.text = "Chicles"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.ChewingGums_category),
                )
            }

            TypesCategoriesMachines.Dianas -> {
                tvCategoryName.text = "Dianas"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.dianas_category),
                )
            }

            TypesCategoriesMachines.TableFootball -> {
                tvCategoryName.text = "Futbolin"

                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.tablefootball_category),
                )
            }

            TypesCategoriesMachines.interactiveMachines -> {
                tvCategoryName.text = "Interactivas"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.interactiveMachines_category),
                )
            }

            TypesCategoriesMachines.sportsMachines -> {
                tvCategoryName.text = "Deportes"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.sportsMachines_category),
                )
            }

            TypesCategoriesMachines.Others -> {
                tvCategoryName.text = "Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.others_category),
                )
            }
        }
    }
}
