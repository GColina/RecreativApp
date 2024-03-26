package com.gcolina.recreativappcangreesl.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcolina.recreativappcangreesl.addAssets.AddAssetsActivity
import com.gcolina.recreativappcangreesl.data.model.AssetModel
import com.gcolina.recreativappcangreesl.databinding.ActivityHomeBinding
import com.gcolina.recreativappcangreesl.detail.DetailAssetActivity
import com.gcolina.recreativappcangreesl.home.components.rvAssets.AssetsAdapter
import com.gcolina.recreativappcangreesl.home.viewModel.HomeViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ASSETMODEL = "extra_asset_model"
    }

    private lateinit var assetAdapter: AssetsAdapter
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        intiObservers()
        initUI()
    }

    override fun onResume() {
        super.onResume()
        getAsset()
    }

    private fun intiObservers() {
        viewModel.assets.observe(this) {
            assetAdapter.updateItems(it)
        }
    }

    private fun initListeners() {
        binding.fabAddMachine.setOnClickListener { navigateToAddAsset() }
    }

    private fun initUI() {
        assetAdapter = AssetsAdapter { assetModel -> navigateToDetail(assetModel) }
        binding.rvAssets.setHasFixedSize(true)
        binding.rvAssets.layoutManager = LinearLayoutManager(this)
        binding.rvAssets.adapter = assetAdapter
    }

    private fun getAsset() {
        val database = Firebase.database
        viewModel.getAssets(database)
    }

    private fun navigateToAddAsset() {
        val intent = Intent(this, AddAssetsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToDetail(assetModel: AssetModel) {
        val intent = Intent(this, DetailAssetActivity::class.java)
        intent.putExtra(EXTRA_ASSETMODEL, assetModel)
        startActivity(intent)
    }
}
