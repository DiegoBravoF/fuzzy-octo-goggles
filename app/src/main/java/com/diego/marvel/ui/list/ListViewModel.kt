package com.diego.marvel.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.marvel.domain.GetCharactersUseCase
import com.diego.marvel.domain.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ListUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val characters = getCharactersUseCase()
            if (characters.isSuccess) {
                _uiState.update {
                    _uiState.value.copy(
                        loading = false,
                        list = characters.getOrElse { emptyList() }
                    )
                }
            }
        }
    }
}

data class ListUIState(
    val loading: Boolean = true,
    val list: List<CharacterModel> = emptyList()
)