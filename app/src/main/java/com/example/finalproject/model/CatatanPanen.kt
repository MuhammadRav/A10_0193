package com.example.finalproject.model

import kotlinx.serialization.Serializable

@Serializable
data class CatatanPanen(
    val id_panen: String,
    val id_tanaman: String,
    val tanggal_panen: String,
    val jumlah_panen: String,
    val keterangan: String
)

@Serializable
data class AllCatatanPanenResponse(
    val status: Boolean,
    val message: String,
    val data: List<CatatanPanen>
)

@Serializable
data class CatatanPanenDetailResponse(
    val status: Boolean,
    val message: String,
    val data: CatatanPanen
)