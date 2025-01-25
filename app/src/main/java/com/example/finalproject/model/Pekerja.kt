package com.example.finalproject.model

import kotlinx.serialization.Serializable

@Serializable
data class Pekerja(
    val id_pekerja: String,
    val nama_pekerja: String,
    val jabatan : String,
    val kontak_pekerja: String
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