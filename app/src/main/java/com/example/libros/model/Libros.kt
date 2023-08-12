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
    val volumeInfo: VolumeInfo,
    val description: String,
    val imageLinks: ImageLinks
)

@Serializable
data class VolumeInfo (
    val title: String,
    val authors: List<String>
)

@Serializable
data class ImageLinks (
    val smallThumbnail: String,
    val thumbnail: String
)
