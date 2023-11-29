package com.realestate.propertyweb.list

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.realestate.propertyweb.R
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class ListFragmentTest : KoinTest {

    private val viewModel = mockk<ListViewModel>(relaxed = true) {
        every { state } returns MutableStateFlow(ListViewModel.UIState.Loading)
    }

    private val module = module {
        factory { viewModel }
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        loadKoinModules(module)
    }

    @After
    fun tearDown() {
        unloadKoinModules(module)
    }

    @Test
    fun shouldTriggerViewModel_onScreenLoaded() {
        val fragmentScenario = launchFragmentInContainer<ListFragment>()

        fragmentScenario.moveToState(Lifecycle.State.RESUMED)

        verify {
            viewModel.onScreenLoaded()
        }
    }

    @Test
    fun shouldTriggerViewModelMethod_OnPropertyItemIsSelected() {
        val property = Property(
            bedrooms = 4,
            city = "Brussels",
            id = 1,
            area = 500,
            url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
            price = 400000,
            professional = "John Doe",
            propertyType = "House",
            offerType = 1,
            rooms = 4
        )
        every { viewModel.state } returns MutableStateFlow(
            ListViewModel.UIState.Content(listOf(property))
        )
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        setupPropertiesListFragmentWith(navController)

        with(composeTestRule) {
            onNodeWithText("John Doe").performClick()
        }

        navController.currentDestination?.id.let {
            assert(it == R.id.propertyDetailsFragment)
        }
    }

    private fun setupPropertiesListFragmentWith(navController: TestNavHostController) {
        val fragmentScenario = launchFragmentInContainer<ListFragment>()
        fragmentScenario.moveToState(Lifecycle.State.RESUMED)
        fragmentScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }
}