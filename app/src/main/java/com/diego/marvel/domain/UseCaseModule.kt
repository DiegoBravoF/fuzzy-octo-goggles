package com.diego.marvel.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetCharactersUseCase(
        characterRepository: CharacterRepository
    ): GetCharactersUseCase = GetCharactersUseCaseImpl(characterRepository = characterRepository)

    @Provides
    @Singleton
    fun provideGetCharacterByIdUseCase(
        characterRepository: CharacterRepository
    ): GetCharacterByIdUseCase =
        GetCharacterByIdUseCaseImpl(characterRepository = characterRepository)
}