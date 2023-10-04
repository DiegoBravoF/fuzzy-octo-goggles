package com.diego.marvel.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.marvel.domain.GetCharacterByIdUseCase
import com.diego.marvel.domain.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {
    private val userId: String = checkNotNull(savedStateHandle["characterId"])

    private val _uiState = MutableStateFlow(DetailUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val character = getCharacterByIdUseCase(userId)
            if (character.isSuccess) {
                _uiState.update {
                    _uiState.value.copy(
                        loading = false,
                        character = character.getOrNull()
                    )
                }
            }
        }
    }
}

data class DetailUIState(
    val loading: Boolean = true,
    val character: CharacterModel? = null,
)