package com.diego.marvel.data

import com.diego.marvel.data.api.toDomain
import com.diego.marvel.mocks.characters
import org.junit.Assert
import org.junit.Test

class CharacterDataWrapperTest {
    @Test
    fun `all params are the same when conver Character on CharacterModel`() {
        val chars = characters.toDomain()
        Assert.assertEquals(1233, chars[0].id)
        Assert.assertEquals("Name", chars[0].name)
        Assert.assertEquals("asdfasd", chars[0].description)
        Assert.assertEquals("path.png", chars[0].image)
    }
}