package com.example.recreativappcangreesl.rvMachines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recreativappcangreesl.R

class MachinesAdapter (private val machines:List<Machine>) : RecyclerView.Adapter<MachinesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MachinesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_machines, parent, false)
        return MachinesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MachinesViewHolder, position: Int) {
        holder.render(machines[position])
    }

    override fun getItemCount() = machines.size

}
