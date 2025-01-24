package com.example.finalproject.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class AktivitasPertanian(
    val idAktivitas: String,
    val idTanaman: String,
    val idPekerja: String,
    @Contextual
    val tanggalAktivitas: Date,
    val deskripsiAktivitas: String
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