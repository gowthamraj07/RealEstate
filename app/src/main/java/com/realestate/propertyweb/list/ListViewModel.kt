package com.realestate.propertyweb.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ListViewModel(private val repository: PropertyRepository) : ViewModel() {
    private val _state: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val state: StateFlow<UIState> = _state

    fun onScreenLoaded() {
        viewModelScope.launch {
            try {
                val properties = repository.getProperties()
                _state.update {
                    UIState.Content(properties)
                }
            } catch (exception: Exception) {
                _state.update {
                    UIState.Error(exception)
                }
            }
        }
    }

    sealed interface UIState {
        data class Error(val exception: Exception) : UIState
        data class Content(val properties: List<Property>) : UIState

        data object Loading: UIState
    }
}
