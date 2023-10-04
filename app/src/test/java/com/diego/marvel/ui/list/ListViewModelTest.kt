package com.diego.marvel.ui.list

import com.diego.marvel.domain.GetCharactersUseCase
import com.diego.marvel.mocks.charactersModelResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test


class ListViewModelTest {
    private lateinit var listViewModel: ListViewModel
    private val getCharactersUseCase: GetCharactersUseCase = mockk(relaxed = true)

    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(newSingleThreadContext("UI thread"))
    }

    @Test
    fun `when init listViewModel call getCharactersUseCase one time`() = runTest {
        coEvery { getCharactersUseCase() } returns charactersModelResult
        listViewModel = ListViewModel(getCharactersUseCase)
        coVerify(atLeast = 1) { listViewModel.getCharactersUseCase() }
    }
}