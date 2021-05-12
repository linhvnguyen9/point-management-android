package com.btntrung.pointmanagement.presentation.di

import com.btntrung.pointmanagement.presentation.manager.ManagerMainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val presentationModule = module {
    viewModel { ManagerMainViewModel(get(), get()) }
}