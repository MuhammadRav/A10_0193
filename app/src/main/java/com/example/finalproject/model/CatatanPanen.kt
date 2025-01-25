package com.example.finalproject.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class CatatanPanen(
    val id_panen: String,
    val id_tanaman: String,
    @Contextual
    val tanggal_panen: Date,
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