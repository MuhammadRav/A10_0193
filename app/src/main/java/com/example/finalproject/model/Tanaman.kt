package com.example.finalproject.model

import kotlinx.serialization.Serializable

@Serializable
data class Tanaman(
    val id_tanaman: String,
    val nama_tanaman: String,
    val periode_tanam : String,
    val deskripsi_tanaman: String
    )

@Serializable
data class AllTanamanResponse(
    val status: Boolean,
    val message: String,
    val data: List<Tanaman>
)

@Serializable
data class TanamanDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Tanaman
)