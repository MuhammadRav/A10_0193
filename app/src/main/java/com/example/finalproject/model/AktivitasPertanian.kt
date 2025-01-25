package com.example.finalproject.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class AktivitasPertanian(
    val id_aktivitas: String,
    val id_tanaman: String,
    val id_pekerja: String,
    @Contextual
    val tanggal_aktivitas: Date,
    val deskripsi_aktivitas: String
)

@Serializable
data class AllAktivitasPertanianResponse(
    val status: Boolean,
    val message: String,
    val data: List<AktivitasPertanian>
)

@Serializable
data class AktivitasPertanianDetailResponse(
    val status: Boolean,
    val message: String,
    val data: AktivitasPertanian
)