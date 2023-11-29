package com.realestate.propertyweb.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class ListViewModel(private val repository: PropertyRepository) : ViewModel() {
    private val _state: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val state: StateFlow<UIState> = _state

    fun onScreenLoaded() {
        try {
            repository.getProperties()
        } catch (e: Exception) {

        }
    }

    sealed interface UIState {
        data object Loading: UIState
    }
}
