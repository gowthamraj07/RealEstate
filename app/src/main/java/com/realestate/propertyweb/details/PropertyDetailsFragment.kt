package com.realestate.propertyweb.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.realestate.propertyweb.domain.Property

class PropertyDetailsFragment : Fragment() {

    private val args: PropertyDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                DetailsScreen(args.property)
            }
        }
    }
}

@Composable
fun DetailsScreen(property: Property) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Type: ${property.propertyType}",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            property.bedrooms?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "bedrooms: ${it}",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            property.area.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "area: ${it} sq.meter",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            property.city.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "location: ${it}",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            property.price.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "price: ${it} Euros",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            property.professional.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Agent: ${it}",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            property.rooms.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "No. of rooms: ${it}",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        property = Property(
            id = 1,
            propertyType = "Apartment",
            city = "London",
            price = 100000,
            professional = "John Doe",
            url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
            area = 100,
            bedrooms = 3,
            offerType = 1,
            rooms = 5
        )
    )
}
