package com.diego.marvel.mocks

import com.diego.marvel.data.api.Thumbnail
import com.diego.marvel.data.api.toDomain

val thumbnail = Thumbnail(
    extension = "png",
    path = "path"
)
val character = com.diego.marvel.data.api.Character(
    id = 1233,
    description = "asdfasd",
    name = "Name",
    thumbnail = thumbnail
)
val characters = listOf(character)
val characterModelResult = Result.success(character.toDomain())
val charactersModelResult = Result.success(characters.toDomain())