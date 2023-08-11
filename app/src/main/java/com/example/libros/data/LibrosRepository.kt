package com.example.libros.data

import com.example.libros.network.LibrosApiService
import com.example.libros.model.Libros

interface LibrosRepository {
    suspend fun getLibros(): Libros
}

class NetworkLibrosRepository(
    private val librosApiService: LibrosApiService
) : LibrosRepository {
    override suspend fun getLibros(): Libros = librosApiService.getLibros()
}
