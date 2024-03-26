package com.gcolina.recreativappcangreesl.detail.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class DetailViewModel
@Inject
constructor(
    private val assetsRepository: AssetsRepository,
    private val scope: CustomScope
) : ViewModel() {
    private val _saved: MutableLiveData<Unit?> = MutableLiveData(null)
    val saved: LiveData<Unit?> = _saved
    fun updateAsset(database: FirebaseDatabase, asset: AssetModel) {
        scope.launch {
            assetsRepository.updateAsset(database, asset).observe(
                onSuccess = {
                    println("&&----updateAsset $it")
                    _saved.value = Unit
                },
                onError = {
                    println("&&---- No esta bien updateAsset $it")
                    // GOT ERROR
                }
            )
        }
    }
}
