package com.diego.marvel.domain

import com.diego.marvel.domain.model.CharacterModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

interface GetCharacterByIdUseCase {
    suspend operator fun invoke(id: String): Result<CharacterModel>
}

@ActivityRetainedScoped
class GetCharacterByIdUseCaseImpl @Inject constructor(
    private val characterRepository: CharacterRepository
) : GetCharacterByIdUseCase {
    override suspend fun invoke(id: String) = characterRepository.getCharacterById(id)

}