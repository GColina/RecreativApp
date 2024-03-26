package com.gcolina.recreativappcangreesl.home.viewModel

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
class HomeViewModel
@Inject
constructor(
    private val assetsRepository: AssetsRepository,
    private val scope: CustomScope
) : ViewModel() {
    private val _assets: MutableLiveData<List<AssetModel>> = MutableLiveData(emptyList())
    val assets: LiveData<List<AssetModel>> = _assets

    fun getAssets(database: FirebaseDatabase) {
        scope.launch {
            assetsRepository.getAssets(database).observe(
                onSuccess = {
                    println("&&----esta bien HomeView $it")
                    _assets.value = it
                },
                onError = {
                    println("&&---- No esta bien HomeView $it")
                    // GOT ERROR
                }
            )
        }
    }
}
