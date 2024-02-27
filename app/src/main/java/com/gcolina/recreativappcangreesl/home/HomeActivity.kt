package com.gcolina.recreativappcangreesl.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcolina.recreativappcangreesl.databinding.ActivityHomeBinding
import com.gcolina.recreativappcangreesl.detail.DetailActivity
import com.gcolina.recreativappcangreesl.home.RecyclerViews.rvCategories.CategoriesAdapter
import com.gcolina.recreativappcangreesl.home.RecyclerViews.rvCategories.TypesCategoriesMachines
import com.gcolina.recreativappcangreesl.home.RecyclerViews.rvMachines.Machine
import com.gcolina.recreativappcangreesl.home.RecyclerViews.rvMachines.MachinesAdapter
import com.gcolina.recreativappcangreesl.home.viewModel.HomeViewModel
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

        initUI()
        initListeners()
    }

    private fun initListeners() {
        binding.fabAddMachine.setOnClickListener { navigateToDetail() }
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

    private fun navigateToDetail() {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }

}
