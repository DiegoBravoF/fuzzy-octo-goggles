package com.diego.marvel.data.local

import com.diego.marvel.domain.model.CharacterModel

interface CharacterLocalDataSource {
    suspend fun getAllCharacters(): List<CharacterModel>
    suspend fun setCharacters(characters: List<CharacterModel>)
}