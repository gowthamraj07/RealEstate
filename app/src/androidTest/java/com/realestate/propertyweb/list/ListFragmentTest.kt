package com.realestate.propertyweb.list

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class ListFragmentTest: KoinTest {

    private val viewModel = mockk<ListViewModel>(relaxed = true) {
        every { state } returns MutableStateFlow(ListViewModel.UIState.Loading)
    }

    private val module = module {
        factory { viewModel }
    }

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

}