package com.example.recreativappcangreesl.data.repository

import com.example.recreativappcangreesl.data.datasource.AssetsDataSource
import com.example.recreativappcangreesl.data.model.AssetModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AssetsRepositoryImpl
    @Inject
    constructor(
        private val assetsDataSource: AssetsDataSource,
    ) : AssetsRepository {
        override suspend fun getAssets(database: FirebaseDatabase): Flow<Result<List<AssetModel>>> =
            flow {
                emit(assetsDataSource.getAssets(database))
            }

        override suspend fun saveAsset(
            database: FirebaseDatabase,
            asset: AssetModel,
        ): Flow<Result<Unit>> =
            flow {
                emit(assetsDataSource.saveAsset(database, asset))
            }

        override suspend fun removeAsset(
            database: FirebaseDatabase,
            asset: AssetModel,
        ): Flow<Result<Unit>> =
            flow {
                emit(assetsDataSource.removeAsset(database, asset))
            }

        override suspend fun updateAsset(
            database: FirebaseDatabase,
            asset: AssetModel,
        ): Flow<Result<Unit>> =
            flow {
                emit(assetsDataSource.updateAsset(database, asset))
            }
    }
