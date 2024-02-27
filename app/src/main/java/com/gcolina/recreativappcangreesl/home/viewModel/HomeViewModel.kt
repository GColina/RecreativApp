package com.gcolina.recreativappcangreesl.home.viewModel

import androidx.lifecycle.ViewModel
import com.gcolina.recreativappcangreesl.data.common.CustomScope
import com.gcolina.recreativappcangreesl.data.common.observe
import com.gcolina.recreativappcangreesl.data.model.AlertModel
import com.gcolina.recreativappcangreesl.data.model.AssetModel
import com.gcolina.recreativappcangreesl.data.model.EarningsModel
import com.gcolina.recreativappcangreesl.data.repository.AssetsRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val assetsRepository: AssetsRepository,
        private val scope: CustomScope,
    ) : ViewModel() {
        fun getAssets(database: FirebaseDatabase) {
            scope.launch {
                assetsRepository.getAssets(database).observe(
                    onSuccess = {
                        // GOT LIST
                    },
                    onError = {
                        // GOT ERROR
                    },
                )
            }
        }

        fun saveAssets(database: FirebaseDatabase) {
            scope.launch {
                // MOCK ASSETS FOR TESTING PURPOSES
                val asset =
                    AssetModel(
                        image = "image1",
                        installationDate = "date1",
                        installationUser = "pepito1",
                        releaseDate = "release1",
                        storeName = "store1",
                        storeAddress = "add1",
                        storeLocLat = 1.1,
                        storeLocLng = 1.1,
                        ownerName = "name1",
                        ownerPhoneNumber = "pn1",
                        ownerEmail = "email1",
                        earnings =
                            listOf(
                                EarningsModel(
                                    date = "dateEarning",
                                    totalEarnings = "20 pavos",
                                    netEarnings = "10 pavos",
                                ),
                            ),
                        alerts =
                            listOf(
                                AlertModel(
                                    date = "dateAlert",
                                    user = "pepito1",
                                    message = "me muero!",
                                ),
                            ),
                    )
                assetsRepository.saveAsset(database, asset).observe(
                    onSuccess = {
                        // ASSET SAVED
                    },
                    onError = {
                        // SAVING ASSET ERROR
                    },
                )
            }
        }
    }
