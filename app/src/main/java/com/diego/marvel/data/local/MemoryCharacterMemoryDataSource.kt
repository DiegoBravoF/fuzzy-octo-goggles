package com.diego.marvel.data.local

import com.diego.marvel.domain.model.CharacterModel

class MemoryCharacterMemoryDataSource : CharacterLocalDataSource {
    private var characters = listOf<CharacterModel>()
    override suspend fun getAllCharacters(): List<CharacterModel> {
        return characters
    }

    override suspend fun setCharacters(characters: List<CharacterModel>) {
        this.characters = characters
    }

}