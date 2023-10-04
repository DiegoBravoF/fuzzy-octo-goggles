package com.diego.marvel.domain

import com.diego.marvel.data.api.toDomain
import com.diego.marvel.data.local.CharacterLocalDataSource
import com.diego.marvel.data.remote.CharacterRemoteDataSource
import com.diego.marvel.mocks.characters
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharacterRepositoryTest {
    private lateinit var sut: CharacterRepository

    @MockK
    private lateinit var remoteDataSource: CharacterRemoteDataSource

    @MockK
    private lateinit var localDataSource: CharacterLocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut = CharacterRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `when local repository is empty call remote`() = runTest {
        coEvery { localDataSource.getAllCharacters() } returns emptyList()
        coEvery { localDataSource.setCharacters(emptyList()) } returns Unit
        coEvery { remoteDataSource.getAllCharacters() } returns Result.success(emptyList())
        sut.getAllCharacters()
        coVerify(atLeast = 1) { remoteDataSource.getAllCharacters() }
    }

    @Test
    fun `success when call repositoryGetAllCharacters`() = runTest {
        coEvery { localDataSource.getAllCharacters() } returns emptyList()
        coEvery { localDataSource.setCharacters(emptyList()) } returns Unit
        coEvery { remoteDataSource.getAllCharacters() } returns Result.success(emptyList())
        val response = sut.getAllCharacters()
        Assert.assertTrue(response.isSuccess)
    }

    @Test
    fun `failure when call repositoryGetAllCharacters`() = runTest {
        coEvery { localDataSource.getAllCharacters() } returns emptyList()
        coEvery { localDataSource.setCharacters(emptyList()) } returns Unit
        coEvery { remoteDataSource.getAllCharacters() } returns Result.failure(Throwable())
        val response = sut.getAllCharacters()
        Assert.assertTrue(response.isFailure)
    }

    @Test
    fun `failure when call repositoryGetCharacterById with no characters`() = runTest {
        coEvery { localDataSource.getAllCharacters() } returns emptyList()
        val character = sut.getCharacterById("")
        Assert.assertTrue(character.isFailure)
    }

    @Test
    fun `success when call repositoryGetCharacterById with characters`() = runTest {
        coEvery { localDataSource.getAllCharacters() } returns characters.toDomain()
        val character = sut.getCharacterById("1233")
        Assert.assertTrue(character.isSuccess)
    }
}