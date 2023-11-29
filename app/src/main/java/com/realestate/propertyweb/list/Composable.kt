package com.realestate.propertyweb.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

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
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
                }
            }

            is ListViewModel.UIState.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Error: ${state.exception.message}",
                        modifier = Modifier.align(alignment = Alignment.Center)
                    )
                }
            }

            is ListViewModel.UIState.Content -> {
                PropertyList(properties = state.properties)
            }
        }
    }
}

@Composable
fun PropertyList(properties: List<Property>) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        properties.forEach { property ->
            PropertyItem(property = property)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PropertyItem(property: Property) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            AsyncImage(
                model = property.url,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .width(150.dp)
                    .height(150.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = property.propertyType,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = property.city,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "${property.price} sq.m",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview
@Composable
fun PropertyItemPreview() {
    PropertyItem(
        property = Property(
            bedrooms = 3,
            city = "London",
            id = 1,
            area = 100,
            url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
            price = 100000,
            professional = "John Doe",
            propertyType = "House",
            offerType = 1,
            rooms = 5
        )
    )
}
