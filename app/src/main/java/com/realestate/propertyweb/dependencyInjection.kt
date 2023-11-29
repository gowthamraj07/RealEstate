package com.realestate.propertyweb

import com.realestate.propertyweb.list.ListViewModel
import com.realestate.propertyweb.list.PropertyRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    factory { PropertyRepository() }
    viewModel { ListViewModel(get()) }
}