package com.example.recreativappcangreesl.data.repository

import com.example.recreativappcangreesl.data.model.AssetModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow

interface AssetsRepository {
    suspend fun getAssets(database: FirebaseDatabase): Flow<Result<List<AssetModel>>>

    suspend fun saveAsset(
        database: FirebaseDatabase,
        asset: AssetModel,
    ): Flow<Result<Unit>>

    suspend fun removeAsset(
        database: FirebaseDatabase,
        asset: AssetModel,
    ): Flow<Result<Unit>>

    suspend fun updateAsset(
        database: FirebaseDatabase,
        asset: AssetModel,
    ): Flow<Result<Unit>>
}
