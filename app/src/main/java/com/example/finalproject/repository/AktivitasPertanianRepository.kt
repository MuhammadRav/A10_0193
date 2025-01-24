package com.example.finalproject.repository

import com.example.finalproject.model.AktivitasPertanian
import com.example.finalproject.model.AllAktivitasPertanianResponse
import com.example.finalproject.service.AktivitasPertanianService
import okio.IOException

interface AktivitasPertanianRepository {
    suspend fun getAktivitasPertanian(): AllAktivitasPertanianResponse
    suspend fun insertAktivitasPertanian(aktivitasPertanian: AktivitasPertanian)
    suspend fun updateAktivitasPertanian(idAktivitas: String, aktivitasPertanian: AktivitasPertanian)
    suspend fun deleteAktivitasPertanian(idAktivitas: String)
    suspend fun getAktivitasPertanianById(idAktivitas: String): AktivitasPertanian
}

class NetworkAktivitasPertanianRepository(
    private val aktivitasPertanianApiService: AktivitasPertanianService
) : AktivitasPertanianRepository {

    override suspend fun insertAktivitasPertanian(aktivitasPertanian: AktivitasPertanian) {
        aktivitasPertanianApiService.insertAktivitas(aktivitasPertanian)
    }

    override suspend fun updateAktivitasPertanian(idAktivitas: String, aktivitasPertanian: AktivitasPertanian) {
        aktivitasPertanianApiService.updateAktivitas(idAktivitas, aktivitasPertanian)
    }

    override suspend fun deleteAktivitasPertanian(idAktivitas: String) {
        try {
            val response = aktivitasPertanianApiService.deleteAktivitas(idAktivitas)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete Aktivitas Pertanian. HTTP Status code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getAktivitasPertanian(): AllAktivitasPertanianResponse =
        aktivitasPertanianApiService.getAllAktivitas()

    override suspend fun getAktivitasPertanianById(idAktivitas: String): AktivitasPertanian {
        return aktivitasPertanianApiService.getAktivitasById(idAktivitas).data
    }
}
