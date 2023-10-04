package com.diego.marvel.data.api

import com.diego.marvel.domain.model.CharacterModel

data class CharacterDataWrapper(
    val data: CharacterDataContainer
)

data class CharacterDataContainer(
    val results: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

data class Thumbnail(
    val path: String,
    val extension: String
)

fun List<Character>.toDomain(): List<CharacterModel> {
    return map {
        it.toDomain()
    }
}

fun Character.toDomain(): CharacterModel {
    return CharacterModel(
        id = id,
        name = name,
        description = description,
        image = thumbnail.path.replace("http", "https").plus(".")
            .plus(thumbnail.extension)
    )
}