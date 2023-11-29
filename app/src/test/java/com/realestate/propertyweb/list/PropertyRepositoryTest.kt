package com.realestate.propertyweb.list

import com.realestate.propertyweb.api.Item
import com.realestate.propertyweb.api.PropertiesDto
import com.realestate.propertyweb.api.PropertyApi
import com.realestate.propertyweb.api.PropertyMapper
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.RandomSource
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.string
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class PropertyRepositoryTest: StringSpec({

    val api = mockk<PropertyApi>(relaxed = true)
    val mapper = mockk<PropertyMapper>(relaxed = true)

    val repository = PropertyRepository(api, mapper)

    "trigger api call to get list of properties" {
        repository.getProperties()

        coVerify {
            api.getProperties()
        }
    }

    "pass property list to mapper when api call is successful" {
        val propertiesDto = Arb.propertiesDtoArb.gen()
        coEvery { api.getProperties() } returns propertiesDto

        repository.getProperties()

        verify {
            mapper.map(propertiesDto)
        }
    }

    "return property list returned by the mapper, when api call and mapping is successful" {
        val propertiesDto = Arb.propertiesDtoArb.gen()
        coEvery { api.getProperties() } returns propertiesDto
        val properties = Arb.property.genList()
        every { mapper.map(propertiesDto) } returns properties

        val result = repository.getProperties()

        result shouldBe properties
    }

    "throw exception when mapper throws exception" {
        val propertiesDto = Arb.propertiesDtoArb.gen()
        coEvery { api.getProperties() } returns propertiesDto
        val exception = Exception("Something went wrong")
        every { mapper.map(propertiesDto) } throws exception

        val result = runCatching { repository.getProperties() }

        result.exceptionOrNull() shouldBe exception
    }
})

val Arb.Companion.propertiesDtoArb: Arb<PropertiesDto>
    get() = arbitrary {
        val items = propertyItemDtoArb.genList()
        PropertiesDto(
            items = items,
            totalCount = items.size
        )
    }

val Arb.Companion.propertyItemDtoArb: Arb<Item>
    get() = arbitrary {
        Item(
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

fun <T> Arb<T>.genList(count: Int = 10) : List<T> {
    return mutableListOf<T>().apply {
        repeat(count) {
            add(sample(RandomSource.default()).value)
        }
    }.toList()
}