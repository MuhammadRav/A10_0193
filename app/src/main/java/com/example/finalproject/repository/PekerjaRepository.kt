package com.example.finalproject.repository

import com.example.finalproject.model.AllPekerjaResponse
import com.example.finalproject.model.Pekerja
import com.example.finalproject.service.PekerjaService
import okio.IOException

interface PekerjaRepository {
    suspend fun getPekerja(): AllPekerjaResponse
    suspend fun insertPekerja(pekerja: Pekerja)
    suspend fun updatePekerja(id_pekerja: String, pekerja: Pekerja)
    suspend fun deletePekerja(id_pekerja: String)
    suspend fun getPekerjaById(id_pekerja: String): Pekerja
}

class NetworkPekerjaRepository(
    private val pekerjaApiService: PekerjaService
) : PekerjaRepository {

    override suspend fun insertPekerja(pekerja: Pekerja) {
        pekerjaApiService.insertPekerja(pekerja)
    }

    override suspend fun updatePekerja(id_pekerja: String, pekerja: Pekerja) {
        pekerjaApiService.updatePekerja(id_pekerja, pekerja)
    }

    override suspend fun deletePekerja(id_pekerja: String) {
        try {
            val response = pekerjaApiService.deletePekerja(id_pekerja)
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

    override suspend fun getPekerjaById(id_pekerja: String): Pekerja {
        return pekerjaApiService.getPekerjaById(id_pekerja).data
    }
}
