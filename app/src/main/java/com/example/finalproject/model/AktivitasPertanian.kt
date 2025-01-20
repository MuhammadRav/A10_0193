package com.example.finalproject.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class AktivitasPertanian(
    @SerialName("id_aktivitas")
    val idAktivitas: String,

    @SerialName("id_tanaman")
    val idTanaman: String,

    @SerialName("id_pekerja")
    val idPekerja: String,

    @SerialName("tanggal_aktivitas")
    @Contextual
    val tanggalAktivitas: Date,

    @SerialName("deskripsi_aktivitas")
    val deskripsiAktivitas: String
)