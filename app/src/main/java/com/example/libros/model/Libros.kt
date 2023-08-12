package com.example.libros.model

import kotlinx.serialization.Serializable

@Serializable
data class Libros (
    val kind: String,
    val totalItems: Int,
    val items: List<Libro>
)

@Serializable
data class Libro (
    val kind: String,
    val id: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo,
)

@Serializable
data class VolumeInfo (
    val title: String,
    val authors: List<String>,
    //Estas propiedades poden vir a nulo
    val description: String? = null,
    val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks (
    val smallThumbnail: String,
    val thumbnail: String
)
