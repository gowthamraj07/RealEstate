package com.realestate.propertyweb.list

import com.realestate.propertyweb.api.PropertyApi
import com.realestate.propertyweb.api.PropertyDto
import com.realestate.propertyweb.api.PropertyMapper
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.RandomSource
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.string
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class PropertyRepositoryTest: StringSpec({

    val api = mockk<PropertyApi>(relaxed = true)
    val mapper = mockk<PropertyMapper>(relaxed = true)

    val repository = PropertyRepository(api, mapper)

    "trigger api call to get list of properties" {
        repository.getProperties()

        verify {
            api.getProperties()
        }
    }

    "pass property list to mapper when api call is successful" {
        val propertyDtos = Arb.propertyDtoArb.genList()
        every { api.getProperties() } returns propertyDtos

        repository.getProperties()

        verify {
            mapper.map(propertyDtos)
        }
    }

    "return property list returned by the mapper, when api call and mapping is successful" {
        val propertyDtos = Arb.propertyDtoArb.genList()
        every { api.getProperties() } returns propertyDtos
        val properties = Arb.property.genList()
        every { mapper.map(propertyDtos) } returns properties

        val result = repository.getProperties()

        result shouldBe properties
    }

    "throw exception when mapper throws exception" {
        val propertyDtos = Arb.propertyDtoArb.genList()
        every { api.getProperties() } returns propertyDtos
        val exception = Exception("Something went wrong")
        every { mapper.map(propertyDtos) } throws exception

        val result = runCatching { repository.getProperties() }

        result.exceptionOrNull() shouldBe exception
    }
})

private val Arb.Companion.propertyDtoArb: Arb<PropertyDto>
    get() = arbitrary {
        PropertyDto(
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