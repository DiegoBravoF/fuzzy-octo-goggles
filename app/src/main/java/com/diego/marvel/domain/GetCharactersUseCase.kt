package com.diego.marvel.domain

import com.diego.marvel.domain.model.CharacterModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

interface GetCharactersUseCase {
    suspend operator fun invoke(): Result<List<CharacterModel>>
}

@ActivityRetainedScoped
class GetCharactersUseCaseImpl @Inject constructor(
    private val characterRepository: CharacterRepository
) : GetCharactersUseCase {
    override suspend fun invoke() = characterRepository.getAllCharacters()

}