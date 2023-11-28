package com.realestate.propertyweb.list

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import com.realestate.propertyweb.KoinTestRule
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module

class ListFragmentTest {

    private val viewModel = mockk<ListViewModel>(relaxed = true)

    private val module = module {
        factory { viewModel }
    }

    @get:Rule
    val koinTestRule = KoinTestRule(
        modules = listOf(module)
    )

    @Test
    fun shouldTriggerViewModel_onScreenLoaded() {
        val fragmentScenario = launchFragmentInContainer<ListFragment>()

        fragmentScenario.moveToState(Lifecycle.State.RESUMED)

        verify {
            viewModel.onScreenLoaded()
        }
    }

}