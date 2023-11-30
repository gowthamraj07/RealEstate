package com.realestate.propertyweb.details

import androidx.compose.ui.test.junit4.createComposeRule
import com.karumi.shot.ScreenshotTest
import com.realestate.propertyweb.domain.Property
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

// ./gradlew executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.class=com.realestate.propertyweb.details.PropertyDetailsScreenshotTest -Precord
// ./gradlew executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.class=com.realestate.propertyweb.details.PropertyDetailsScreenshotTest
class PropertyDetailsScreenshotTest : ScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun detailsScreen() {
        with(composeTestRule) {
            setContent {
                DetailsScreen(
                    property = Property(
                        bedrooms = 2,
                        city = "Brussels",
                        id = 1,
                        area = 100,
                        propertyType = "House",
                        url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                        price = 100000,
                        professional = "John Doe",
                        offerType = 1,
                        rooms = 5
                    )
                )
            }

            runBlocking {
                delay(2000)
            }

            compareScreenshot(this)
        }
    }
}