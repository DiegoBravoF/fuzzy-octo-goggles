package com.diego.marvel.data

import com.diego.marvel.data.api.MarvelService
import com.diego.marvel.data.local.CharacterLocalDataSource
import com.diego.marvel.data.local.MemoryCharacterMemoryDataSource
import com.diego.marvel.data.remote.CharacterRemoteDataSource
import com.diego.marvel.data.remote.CharacterRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesCharacterRemoteDataSource(
        apiService: MarvelService
    ): CharacterRemoteDataSource =
        CharacterRemoteDataSourceImpl(apiService = apiService)

    @Singleton
    @Provides
    fun providesCharacterLocalDataSource(
        apiService: MarvelService
    ): CharacterLocalDataSource =
        MemoryCharacterMemoryDataSource()
}