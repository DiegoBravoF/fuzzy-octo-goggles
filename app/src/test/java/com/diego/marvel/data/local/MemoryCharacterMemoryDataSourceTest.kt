package com.diego.marvel.data.local

import com.diego.marvel.data.api.toDomain
import com.diego.marvel.mocks.characters
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MemoryCharacterMemoryDataSourceTest {
    private val sut: MemoryCharacterMemoryDataSource = MemoryCharacterMemoryDataSource()

    @Before
    fun setUp() {
        runBlocking {
            sut.setCharacters(emptyList())
        }
    }

    @Test
    fun `return empty list if characters are empty`() = runTest {
        Assert.assertTrue(sut.getAllCharacters().isEmpty())
    }

    @Test
    fun `return list if characters are filled`() = runTest {
        sut.setCharacters(characters = characters.toDomain())
        Assert.assertEquals(characters.toDomain(), sut.getAllCharacters())
    }
}