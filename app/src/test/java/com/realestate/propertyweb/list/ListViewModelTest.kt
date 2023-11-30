package com.realestate.propertyweb.list

import app.cash.turbine.test
import com.realestate.propertyweb.domain.Property
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.RandomSource
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.string
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

class ListViewModelTest : StringSpec({
    isolationMode = IsolationMode.InstancePerLeaf
    coroutineTestScope = true

    val repository = mockk<PropertyRepository>(relaxed = true)
    val viewModel = ListViewModel(repository)

    beforeTest {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    afterTest {
        Dispatchers.resetMain()
    }

    "should emit Loading state on view model initialization" {
        viewModel.state.value shouldBe ListViewModel.UIState.Loading
    }

    "trigger repository to get list of properties on screen loaded" {
        viewModel.onScreenLoaded()

        coVerify { repository.getProperties() }
    }

    "emit state as loading followed by error when repository throws exception" {
        val exception = Exception("Something went wrong")
        coEvery { repository.getProperties() } throws exception

        viewModel.state.test {
            viewModel.onScreenLoaded()

            awaitItem() shouldBe ListViewModel.UIState.Loading
            awaitItem() shouldBe ListViewModel.UIState.Error(exception)
        }
    }

    "emit state as loading followed by Content when repository returns list of properties" {
        val properties = listOf(
            Arb.property.gen()
        )
        coEvery { repository.getProperties() } returns properties

        viewModel.state.test {
            viewModel.onScreenLoaded()

            awaitItem() shouldBe ListViewModel.UIState.Loading
            awaitItem() shouldBe ListViewModel.UIState.Content(properties)
        }
    }
})

val Arb.Companion.property: Arb<Property>
    get() = arbitrary {
        Property(
            bedrooms = Arb.int().gen(),
            city = Arb.string().gen(),
            id = Arb.int().gen(),
            area = Arb.int().gen(),
            url = Arb.string().gen(),
            price = Arb.int().gen(),
            professional = Arb.string().gen(),
            propertyType = Arb.string().gen(),
            offerType = Arb.int().gen(),
            rooms = Arb.int().gen(),
        )
    }

internal fun <T> Arb<T>.gen(): T {
    return sample(RandomSource.default()).value
}