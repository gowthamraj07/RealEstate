package com.realestate.propertyweb.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

internal class ListViewModel(private val repository: PropertyRepository) : ViewModel() {
    private val _state: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val state: StateFlow<UIState> = _state

    fun onScreenLoaded() {
        try {
            repository.getProperties()
        } catch (exception: Exception) {
            _state.update {
                UIState.Error(exception)
            }
        }
    }

    sealed interface UIState {
        data class Error(val exception: Exception) : UIState

        data object Loading: UIState
    }
}
