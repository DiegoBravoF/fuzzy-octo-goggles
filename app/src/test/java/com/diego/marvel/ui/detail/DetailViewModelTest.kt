package com.diego.marvel.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.diego.marvel.domain.GetCharacterByIdUseCase
import com.diego.marvel.mocks.characterModelResult
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


class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase = mockk(relaxed = true)
    private val savedStateHandle: SavedStateHandle = SavedStateHandle(
        initialState = hashMapOf(Pair("characterId", ""))
    )

    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(newSingleThreadContext("UI thread"))
    }

    @Test
    fun `when init detailViewModel call getCharacterByIdUseCase one time`() = runTest {
        coEvery { getCharacterByIdUseCase("") } returns characterModelResult
        viewModel = DetailViewModel(savedStateHandle, getCharacterByIdUseCase)
        coVerify(atLeast = 1) { getCharacterByIdUseCase("") }
    }
}