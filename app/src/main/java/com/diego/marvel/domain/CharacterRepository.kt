package com.diego.marvel.domain

import com.diego.marvel.data.api.Character
import com.diego.marvel.data.local.CharacterLocalDataSource
import com.diego.marvel.data.remote.CharacterRemoteDataSource
import com.diego.marvel.domain.model.CharacterModel
import javax.inject.Inject

interface CharacterRepository {
    suspend fun getAllCharacters(): Result<List<CharacterModel>>
    suspend fun getCharacterById(id: String): Result<CharacterModel>
}

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterLocalDataSource
) : CharacterRepository {
    override suspend fun getAllCharacters(): Result<List<CharacterModel>> {

        if (localDataSource.getAllCharacters().isEmpty()) {
            val list = remoteDataSource.getAllCharacters()
            if (list.isFailure) {
                return Result.failure(Throwable("Error"))
            } else {
                localDataSource.setCharacters(list.getOrDefault(emptyList()))
            }
        }
        return Result.success(localDataSource.getAllCharacters())
    }

    override suspend fun getCharacterById(id: String): Result<CharacterModel> {
        localDataSource.getAllCharacters().find { it.id == id.toInt() }?.let {
            return Result.success(it)
        }
        return Result.failure(Throwable("ERROR"))
    }
}