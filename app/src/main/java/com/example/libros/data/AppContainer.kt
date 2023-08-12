package com.example.libros.data

import com.example.libros.network.LibrosApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val librosRepository: LibrosRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://www.googleapis.com/books/v1/"

    //Nova opción para non ter que definir tódalas proiedades da orixe: { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json{ ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: LibrosApiService by lazy {
        retrofit.create(LibrosApiService::class.java)
    }

    override val librosRepository: LibrosRepository by lazy {
        NetworkLibrosRepository(retrofitService)
    }
}

