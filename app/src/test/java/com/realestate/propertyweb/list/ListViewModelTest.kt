package com.realestate.propertyweb.list

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ListViewModelTest: StringSpec({
    isolationMode = IsolationMode.InstancePerLeaf
    val viewModel = ListViewModel()

    "should emit Loading state on view model initialization" {
        viewModel.state.value shouldBe ListViewModel.UIState.Loading
    }
})