package com.gcolina.recreativappcangreesl.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcolina.recreativappcangreesl.componentsRv.rvAlerts.AlertsAdapter
import com.gcolina.recreativappcangreesl.componentsRv.rvEarnings.EarningAdapter
import com.gcolina.recreativappcangreesl.data.model.AlertModel
import com.gcolina.recreativappcangreesl.data.model.AssetModel
import com.gcolina.recreativappcangreesl.data.model.EarningsModel
import com.gcolina.recreativappcangreesl.databinding.ActivityDetailAssetsBinding
import com.gcolina.recreativappcangreesl.detail.components.AlertDialog
import com.gcolina.recreativappcangreesl.detail.components.EarnningDialog
import com.gcolina.recreativappcangreesl.detail.viewModel.DetailViewModel
import com.gcolina.recreativappcangreesl.home.HomeActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class DetailAssetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAssetsBinding
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var earningAdapter: EarningAdapter
    private lateinit var alertsAdapter: AlertsAdapter
    private lateinit var assetModel: AssetModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAssetsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intiObservers()
        initlisteners()
        initUi()
        getAssetFromIntent(intent)
        showTexts()
    }

    private fun showTexts() {
        binding.tvinstallationDate.text = assetModel.installationDate
        binding.tvstoreName.text = assetModel.storeName
        binding.tvOwnerName.text = assetModel.ownerName
        binding.tvOwnerPhone.text = assetModel.ownerPhoneNumber
        binding.tvOwnerEmail.text = assetModel.ownerEmail
    }

    private fun intiObservers() {
        viewModel.saved.observe(this) {
            it?.let {
                Toast.makeText(
                    this,
                    "Se ha guardado.",
                    Toast.LENGTH_SHORT
                ).show()
                // .
                /*finish()*/
            }
        }
    }

    private fun getAssetFromIntent(intent: Intent) {
        intent.getParcelableExtra<AssetModel>(HomeActivity.EXTRA_ASSETMODEL)?.let {
            assetModel = it
            alertsAdapter.upDateItems(it.alerts)
            earningAdapter.updateItems(it.earnings)
        } ?: run {
            // TODO gestionar error AssetModel Null
        }
    }

    private fun initUi() {
        // Earning Rv
        earningAdapter = EarningAdapter()
        binding.rvEarnings.setHasFixedSize(true)
        binding.rvEarnings.layoutManager = LinearLayoutManager(this)
        binding.rvEarnings.adapter = earningAdapter
        // Alert Rv
        alertsAdapter = AlertsAdapter()
        binding.rvAlerts.setHasFixedSize(true)
        binding.rvAlerts.layoutManager = LinearLayoutManager(this)
        binding.rvAlerts.adapter = alertsAdapter
    }

    private fun initlisteners() {
        binding.btnAddEarnings.setOnClickListener {
            var amountNet = ""
            var amountTotal = ""
            EarnningDialog(onClickListener = { net, total ->
                amountNet = net
                amountTotal = total
                val earningsList = assetModel.earnings.toMutableList()
                earningsList.add(
                    EarningsModel(
                        date = SimpleDateFormat("dd/MM/yyyy HH:mm").format(Date()),
                        totalEarnings = amountTotal,
                        netEarnings = amountNet
                    )
                )
                earningAdapter.updateItems(earningsList)
                viewModel.updateAsset(
                    database = Firebase.database,
                    assetModel.copy(earnings = earningsList)
                )
            }).show(supportFragmentManager, "dialogEarning")
        }
        binding.btnAddAlerts.setOnClickListener {
            var resultAlertDialog = ""
            val auth = Firebase.auth
            val currentUser = auth.currentUser
            AlertDialog(onClickListener = { alert ->
                resultAlertDialog = alert
                val alertsList = assetModel.alerts.toMutableList()
                alertsList.add(
                    AlertModel(
                        date = SimpleDateFormat("dd/MM/yyyy").format(Date()),
                        user = currentUser?.email ?: "Cangrejo no identificado",
                        message = resultAlertDialog
                    )
                )
                alertsAdapter.upDateItems(alertsList)
                viewModel.updateAsset(
                    database = Firebase.database,
                    assetModel.copy(alerts = alertsList)
                )
                Toast.makeText(this, "Se ha guardado la alerta.", Toast.LENGTH_SHORT).show()
            }).show(supportFragmentManager, "alertDialog")
        }
        binding.btnModifier.setOnClickListener { }
        binding.btnUnInstall.setOnClickListener { }
    }
}
