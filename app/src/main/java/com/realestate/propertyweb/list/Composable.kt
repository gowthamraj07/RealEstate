package com.realestate.propertyweb.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
internal fun PropertyListScreen(
    state: ListViewModel.UIState,
    onItemClickListener: (Property) -> Unit
) {
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
                PropertyList(properties = state.properties, onItemClickListener)
            }
        }
    }
}

@Composable
fun PropertyList(properties: List<Property>, onItemClickListener: (Property) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        properties.forEach { property ->
            PropertyItem(property = property, onItemClickListener)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PropertyItem(property: Property, onItemClickListener: (Property) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClickListener(property)
            }
    ) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(property.url)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = property.propertyType,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = property.city,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${property.price} sq.m",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = property.professional,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
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
        ),
        onItemClickListener = {}
    )
}
