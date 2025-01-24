package com.example.finalproject.repository

import com.example.finalproject.model.AllPekerjaResponse
import com.example.finalproject.model.Pekerja
import com.example.finalproject.service.PekerjaService
import okio.IOException

interface PekerjaRepository {
    suspend fun getPekerja(): AllPekerjaResponse
    suspend fun insertPekerja(pekerja: Pekerja)
    suspend fun updatePekerja(idPekerja: String, pekerja: Pekerja)
    suspend fun deletePekerja(idPekerja: String)
    suspend fun getPekerjaById(idPekerja: String): Pekerja
}

class NetworkPekerjaRepository(
    private val pekerjaApiService: PekerjaService
) : PekerjaRepository {

    override suspend fun insertPekerja(pekerja: Pekerja) {
        pekerjaApiService.insertPekerja(pekerja)
    }

    override suspend fun updatePekerja(idPekerja: String, pekerja: Pekerja) {
        pekerjaApiService.updatePekerja(idPekerja, pekerja)
    }

    override suspend fun deletePekerja(idPekerja: String) {
        try {
            val response = pekerjaApiService.deletePekerja(idPekerja)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete Pekerja. HTTP Status code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPekerja(): AllPekerjaResponse =
        pekerjaApiService.getAllPekerja()

    override suspend fun getPekerjaById(idPekerja: String): Pekerja {
        return pekerjaApiService.getPekerjaById(idPekerja).data
    }
}
