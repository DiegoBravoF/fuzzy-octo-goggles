package com.diego.marvel.data

import com.diego.marvel.data.local.CharacterLocalDataSource
import com.diego.marvel.data.remote.CharacterRemoteDataSource
import com.diego.marvel.domain.CharacterRepository
import com.diego.marvel.domain.CharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun providesCharacterRepository(
        characterRemoteDataSource: CharacterRemoteDataSource,
        characterLocalDataSource: CharacterLocalDataSource
    ): CharacterRepository =
        CharacterRepositoryImpl(characterRemoteDataSource, characterLocalDataSource)
}