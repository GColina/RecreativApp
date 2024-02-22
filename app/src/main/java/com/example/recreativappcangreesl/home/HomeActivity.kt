package com.example.recreativappcangreesl.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recreativappcangreesl.databinding.ActivityHomeBinding
import com.example.recreativappcangreesl.home.viewModel.HomeViewModel
import com.example.recreativappcangreesl.rvCategories.CategoriesAdapter
import com.example.recreativappcangreesl.rvCategories.TypesCategoriesMachines
import com.example.recreativappcangreesl.rvMachines.Machine
import com.example.recreativappcangreesl.rvMachines.MachinesAdapter
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val machines =
        mutableListOf(
            Machine("Bar Mangu", "Michel El Cangre", "666666666", TypesCategoriesMachines.Billiards),
            Machine("Bar asd", "ads El Cangre", "666666666", TypesCategoriesMachines.Cars),
            Machine("Bar kjbasd", "gsdfs El Cangre", "666666666", TypesCategoriesMachines.Dianas),
        )

    private val categories =
        listOf(
            TypesCategoriesMachines.Balls,
            TypesCategoriesMachines.Billiards,
            TypesCategoriesMachines.Cars,
            TypesCategoriesMachines.ChewingGums,
            TypesCategoriesMachines.Dianas,
            TypesCategoriesMachines.interactiveMachines,
            TypesCategoriesMachines.Others,
            TypesCategoriesMachines.sportsMachines,
            TypesCategoriesMachines.TableFootball,
        )
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var machinesAdapter: MachinesAdapter

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load the assets here
        val database = Firebase.database
        // viewModel.getAssets(database)

        initComponent()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        binding.fabAddMachine.setOnClickListener {
        }
    }

    private fun initComponent() {
        // rvCategoriesMachines = findViewById(R.id.rvCategoriesMachines)
        // rvMachines = findViewById(R.id.rvMachines)
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
