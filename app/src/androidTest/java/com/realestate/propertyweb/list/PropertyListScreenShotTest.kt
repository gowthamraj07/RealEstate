package com.realestate.propertyweb.list

import androidx.compose.ui.test.junit4.createComposeRule
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test


// ./gradlew executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.class=com.realestate.propertyweb.list.PropertyListScreenShotTest -Precord
class PropertyListScreenShotTest: ScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingState() {
        with(composeTestRule) {
            setContent {
                PropertyListScreen(state = ListViewModel.UIState.Loading)
            }

            compareScreenshot(this)
        }
    }

    @Test
    fun errorState() {
        with(composeTestRule) {
            setContent {
                PropertyListScreen(state = ListViewModel.UIState.Error(Exception("Something went wrong")))
            }

            compareScreenshot(this)
        }
    }

}