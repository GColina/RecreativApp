package com.example.recreativappcangreesl.di

import com.example.recreativappcangreesl.data.common.CustomScope
import com.example.recreativappcangreesl.data.datasource.AssetsDataSource
import com.example.recreativappcangreesl.data.repository.AssetsRepository
import com.example.recreativappcangreesl.data.repository.AssetsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesScope() = CustomScope()

    @Provides
    @Singleton
    fun providesAssetsRepository(assetsDataSource: AssetsDataSource): AssetsRepository = AssetsRepositoryImpl(assetsDataSource)
}
