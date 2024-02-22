package com.example.recreativappcangreesl.data.datasource

import com.example.recreativappcangreesl.data.model.AssetEntity
import com.example.recreativappcangreesl.data.model.AssetModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private const val ASSETS_REFERENCE = "assets"

class AssetsDataSource
    @Inject
    constructor() {
        suspend fun getAssets(database: FirebaseDatabase): Result<List<AssetModel>> {
            return suspendCoroutine { continuation ->
                try {
                    val ref = database.getReference(ASSETS_REFERENCE)

                    ref.addListenerForSingleValueEvent(
                        object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                val jsonString = dataSnapshot.value as? String
                                jsonString?.let {
                                    val assets: List<AssetModel> =
                                        try {
                                            val assetEntity: List<AssetEntity>? =
                                                Gson().fromJson(
                                                    it,
                                                    object : TypeToken<List<AssetEntity>>() {}.type,
                                                )
                                            assetEntity?.map { entity -> entity.toDomain() }
                                                ?: return continuation.resume(Result.failure(Throwable("COULD NOT PARSE!")))
                                        } catch (t: Throwable) {
                                            return continuation.resume(Result.failure(Throwable("ERROR: $t")))
                                        }
                                    continuation.resume(Result.success(assets))
                                } ?: continuation.resume(Result.failure(Throwable("DATA IS EMPTY!")))
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                continuation.resume(Result.failure(databaseError.toException()))
                            }
                        },
                    )
                } catch (t: Throwable) {
                    continuation.resume(Result.failure(t))
                }
            }
        }

        suspend fun saveAsset(
            database: FirebaseDatabase,
            asset: AssetModel,
        ): Result<Unit> {
            // 1. Get all assets
            val allAssetsOP = getAssets(database)
            val allAssets =
                if (allAssetsOP.isSuccess) {
                    allAssetsOP.getOrNull() ?: emptyList()
                } else {
                    return Result.failure(
                        allAssetsOP.exceptionOrNull()
                            ?: Throwable("FAILED TO GET ALL ASSETS WITHOUT ERROR!"),
                    )
                }

            // 2. Convert to mutable list
            val allAssetsMutable = allAssets.toMutableList()

            // 3. Add the new asset
            allAssetsMutable.add(asset)

            // 4. Save the new assets list
            val saveOP = saveAssets(database, allAssetsMutable)
            return if (saveOP.isSuccess) {
                Result.success(Unit)
            } else {
                Result.failure(
                    saveOP.exceptionOrNull() ?: Throwable("FAILED TO SAVE ASSETS WITHOUT ERROR!"),
                )
            }
        }

        suspend fun removeAsset(
            database: FirebaseDatabase,
            asset: AssetModel,
        ): Result<Unit> {
            // 1. Get all assets
            val allAssetsOP = getAssets(database)
            val allAssets =
                if (allAssetsOP.isSuccess) {
                    allAssetsOP.getOrNull() ?: emptyList()
                } else {
                    return Result.failure(
                        allAssetsOP.exceptionOrNull()
                            ?: Throwable("FAILED TO GET ALL ASSETS WITHOUT ERROR!"),
                    )
                }

            // 2. Convert to mutable list
            val allAssetsMutable = allAssets.toMutableList()

            // 3. Remove the asset
            allAssetsMutable.removeIf {
                it.id == asset.id
            }

            // 4. Save the new assets list
            val saveOP = saveAssets(database, allAssetsMutable)
            return if (saveOP.isSuccess) {
                Result.success(Unit)
            } else {
                Result.failure(
                    saveOP.exceptionOrNull() ?: Throwable("FAILED TO SAVE ASSETS WITHOUT ERROR!"),
                )
            }
        }

        suspend fun updateAsset(
            database: FirebaseDatabase,
            asset: AssetModel,
        ): Result<Unit> {
            // 1. Get all assets
            val allAssetsOP = getAssets(database)
            val allAssets =
                if (allAssetsOP.isSuccess) {
                    allAssetsOP.getOrNull() ?: emptyList()
                } else {
                    return Result.failure(
                        allAssetsOP.exceptionOrNull()
                            ?: Throwable("FAILED TO GET ALL ASSETS WITHOUT ERROR!"),
                    )
                }

            // 2. Update asset
            val allAssetsMutable =
                allAssets.toMutableList().map {
                    if (it.id == asset.id) {
                        asset
                    } else {
                        it
                    }
                }

            // 3. Save the new assets list
            val saveOP = saveAssets(database, allAssetsMutable)
            return if (saveOP.isSuccess) {
                Result.success(Unit)
            } else {
                Result.failure(
                    saveOP.exceptionOrNull() ?: Throwable("FAILED TO SAVE ASSETS WITHOUT ERROR!"),
                )
            }
        }

        private suspend fun saveAssets(
            database: FirebaseDatabase,
            assets: List<AssetModel>,
        ): Result<Unit> {
            return suspendCoroutine { continuation ->
                try {
                    val ref = database.getReference(ASSETS_REFERENCE)
                    ref.setValue(Gson().toJson(assets.map { it.toEntity() }))
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                continuation.resume(Result.success(Unit))
                            } else {
                                continuation.resume(
                                    Result.failure(
                                        task.exception ?: Throwable("UNKNOWN ERROR ${task.exception}"),
                                    ),
                                )
                            }
                        }
                } catch (t: Throwable) {
                    continuation.resume(Result.failure(t))
                }
            }
        }
    }
