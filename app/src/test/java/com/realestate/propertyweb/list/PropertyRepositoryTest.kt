package com.realestate.propertyweb.list

import com.realestate.propertyweb.api.PropertyApi
import io.kotest.core.spec.style.StringSpec
import io.mockk.mockk
import io.mockk.verify

class PropertyRepositoryTest: StringSpec({

    val api = mockk<PropertyApi>(relaxed = true)

    val repository = PropertyRepository(api)

    "trigger api call to get list of properties" {
        repository.getProperties()

        verify {
            api.getProperties()
        }
    }
})