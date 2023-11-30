package com.realestate.propertyweb.list

import androidx.compose.ui.test.junit4.createComposeRule
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test


// ./gradlew executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.class=com.realestate.propertyweb.list.PropertyListScreenShotTest -Precord
// ./gradlew executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.class=com.realestate.propertyweb.list.PropertyListScreenShotTest
class PropertyListScreenShotTest : ScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingState() {
        with(composeTestRule) {
            setContent {
                PropertyListScreen(state = ListViewModel.UIState.Loading){}
            }

            compareScreenshot(this)
        }
    }

    @Test
    fun errorState() {
        with(composeTestRule) {
            setContent {
                PropertyListScreen(state = ListViewModel.UIState.Error(Exception("Something went wrong"))){}
            }

            compareScreenshot(this)
        }
    }

    @Test
    fun propertyListState() {
        with(composeTestRule) {
            setContent {
                PropertyListScreen(
                    state = ListViewModel.UIState.Content(
                        properties = listOf(
                            Property(
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
                    )
                ) {}
            }

            compareScreenshot(this)
        }
    }

}