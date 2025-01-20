package com.example.finalproject.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class CatatanPanen(
    @SerialName("id_panen")
    val idPanen: String,

    @SerialName("id_tanaman")
    val idTanaman: String,

    @SerialName("tanggal_panen")
    @Contextual
    val tanggalPanen: Date,

    @SerialName("jumlah_panen")
    val jumlahPanen: String,

    val keterangan: String
)