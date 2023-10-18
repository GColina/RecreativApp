package com.example.recreativappcangreesl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recreativappcangreesl.databinding.ActivityMainBinding
import com.example.recreativappcangreesl.rvCategories.CategoriesAdapter
import com.example.recreativappcangreesl.rvCategories.TypesCategoriesMachines
import com.example.recreativappcangreesl.rvCategories.TypesCategoriesMachines.*
import com.example.recreativappcangreesl.rvMachines.Machine
import com.example.recreativappcangreesl.rvMachines.MachinesAdapter

class MainActivity : AppCompatActivity() {

    private val machines = mutableListOf(
        Machine("Bar Mangu", "Michel El Cangre", "666666666", Billiards),
        Machine("Bar asd", "ads El Cangre", "666666666", Cars),
        Machine("Bar kjbasd", "gsdfs El Cangre", "666666666", Dianas)
    )


    private val categories = listOf(
        Balls,
        Billiards,
        Cars,
        ChewingGums,
        Dianas,
        interactiveMachines,
        Others,
        sportsMachines,
        TableFootball
    )
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var machinesAdapter: MachinesAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponent()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        binding.fabAddMachine.setOnClickListener {

        }
    }

    private fun initComponent() {
        /*rvCategoriesMachines = findViewById(R.id.rvCategoriesMachines)*/
        /*rvMachines = findViewById(R.id.rvMachines)*/

    }


    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories)
        binding.rvCategoriesMachines.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategoriesMachines.adapter = categoriesAdapter


        machinesAdapter = MachinesAdapter(machines)
        binding.rvMachines.layoutManager = LinearLayoutManager(this)
        binding.rvMachines.adapter = machinesAdapter

    }
}