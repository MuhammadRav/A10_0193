package com.example.finalproject.model

import kotlinx.serialization.Serializable

@Serializable
data class Pekerja(
    val idPekerja: String,
    val namaPekerja: String,
    val jabatan : String,
    val kontakPekerja: String
)

@Serializable
data class AllPekerjaResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pekerja>
)

@Serializable
data class PekerjaDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Pekerja
)