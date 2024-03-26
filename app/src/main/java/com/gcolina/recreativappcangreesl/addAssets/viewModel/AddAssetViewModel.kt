package com.gcolina.recreativappcangreesl.addAssets.viewModel

import androidx.lifecycle.ViewModel
import com.gcolina.recreativappcangreesl.data.common.CustomScope
import com.gcolina.recreativappcangreesl.data.common.observe
import com.gcolina.recreativappcangreesl.data.model.AssetModel
import com.gcolina.recreativappcangreesl.data.repository.AssetsRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AddAssetViewModel
@Inject
constructor(
    private val assetsRepository: AssetsRepository,
    private val scope: CustomScope
) : ViewModel() {
    fun saveAssets(database: FirebaseDatabase, asset: AssetModel) {
        scope.launch {
            assetsRepository.saveAsset(database, asset).observe(
                onSuccess = {
                    println("&&----esta bien $asset ")
                },
                onError = {
                    println("&&---- No esta bien $it")
                    // SAVING ASSET ERROR
                }
            )
        }
    }
}
