package com.realestate.propertyweb.list

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify

class ListViewModelTest: StringSpec({
    isolationMode = IsolationMode.InstancePerLeaf


    val repository = mockk<PropertyRepository>(relaxed = true)
    val viewModel = ListViewModel(repository)

    "should emit Loading state on view model initialization" {
        viewModel.state.value shouldBe ListViewModel.UIState.Loading
    }

    "trigger repository to get list of properties on screen loaded" {
        viewModel.onScreenLoaded()

        verify { repository.getProperties() }
    }

    "emit state as error when repository throws exception" {
        val exception = Exception("Something went wrong")
        coEvery { repository.getProperties() } throws exception

        viewModel.onScreenLoaded()

        viewModel.state.value shouldBe ListViewModel.UIState.Error(exception)
    }
})