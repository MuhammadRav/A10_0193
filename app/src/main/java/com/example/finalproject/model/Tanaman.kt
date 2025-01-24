package com.example.finalproject.model

import kotlinx.serialization.Serializable

@Serializable
data class Tanaman(
    val idTanaman: String,
    val namaTanaman: String,
    val periodeTanam : String,
    val deskripsiTanaman: String
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