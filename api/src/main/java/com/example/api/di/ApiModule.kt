package com.example.api.di

import com.example.api.BaseDataSource
import com.example.api.MainApi
import com.example.api.UserDataRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    fun provideUserRepository(api: MainApi): BaseDataSource {
        return UserDataRepository(api, Dispatchers.IO)
    }
    single { get<Retrofit>().create(MainApi::class.java) }

    single { provideUserRepository(get()) }
}



