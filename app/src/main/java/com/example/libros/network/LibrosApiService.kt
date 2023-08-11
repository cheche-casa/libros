package com.example.libros.network

import com.example.libros.model.Libros
import retrofit2.http.GET

interface LibrosApiService {
    @GET("volumes?q=jazz+history&projection=lite") //Este é o subcartafol da URI
    suspend fun getLibros(): Libros
}