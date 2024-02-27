package com.gcolina.recreativappcangreesl.di

import com.gcolina.recreativappcangreesl.data.common.CustomScope
import com.gcolina.recreativappcangreesl.data.datasource.AssetsDataSource
import com.gcolina.recreativappcangreesl.data.repository.AssetsRepository
import com.gcolina.recreativappcangreesl.data.repository.AssetsRepositoryImpl
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
