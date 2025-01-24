package com.example.finalproject.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class CatatanPanen(
    val idPanen: String,
    val idTanaman: String,
    @Contextual
    val tanggalPanen: Date,
    val jumlahPanen: String,
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