package com.realestate.propertyweb.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun PropertyListScreen(state: ListViewModel.UIState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        when (state) {
            is ListViewModel.UIState.Loading -> {
                // show circular progress bar
                Box (modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
                }
            }

            is ListViewModel.UIState.Error -> {
                Box (modifier = Modifier.fillMaxSize()) {
                    Text(text = "Error: ${state.exception.message}", modifier = Modifier.align(alignment = Alignment.Center))
                }
            }

            is ListViewModel.UIState.Content -> {

            }
        }
    }
}