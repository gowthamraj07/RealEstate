package com.realestate.propertyweb

import com.realestate.propertyweb.api.PropertyApi
import com.realestate.propertyweb.api.PropertyMapper
import com.realestate.propertyweb.list.ListViewModel
import com.realestate.propertyweb.list.PropertyRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    single { Retrofit.Builder().baseUrl("https://gsl-apps-technical-test.dignp.com/").build() }
    factory { get<Retrofit>().create(PropertyApi::class.java) }
    factory { PropertyMapper() }
    factory { PropertyRepository(get(), get()) }
    viewModel { ListViewModel(get()) }
}