package com.example.recreativappcangreesl.rvMachines

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.recreativappcangreesl.R

class MachinesViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val tvBarName:TextView = view.findViewById(R.id.tvBarName)
    private val ivMachine:ImageView =  view.findViewById(R.id.ivMachine)
    private val ivNotes:ImageView =  view.findViewById(R.id.ivNotes)
    private val ivLocation:ImageView =  view.findViewById(R.id.ivLocation)
    private val ivAlertTime:ImageView =  view.findViewById(R.id.ivAlertTime)
    private val tvOwnerName:TextView = view.findViewById(R.id.tvOwnerName)
    private val tvOwnerPhone:TextView = view.findViewById(R.id.tvOwnerPhone)
    private val cvCategories:CardView = view.findViewById(R.id.cvCategories)

    fun render(machine: Machine){
        tvBarName.text = machine.BarName
    }
}