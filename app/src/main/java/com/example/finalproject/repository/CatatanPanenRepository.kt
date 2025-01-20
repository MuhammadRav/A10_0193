package com.example.finalproject.repository

import com.example.finalproject.model.CatatanPanen
import com.example.finalproject.service.CatatanPanenService
import okio.IOException

interface CatatanPanenRepository {
    suspend fun getCatatanPanen(): List<CatatanPanen>
    suspend fun insertCatatanPanen(catatanPanen: CatatanPanen)
    suspend fun updateCatatanPanen(idPanen: String, catatanPanen: CatatanPanen)
    suspend fun deleteCatatanPanen(idPanen: String)
    suspend fun getCatatanPanenById(idPanen: String): CatatanPanen
}

class NetworkCatatanPanenRepository(
    private val catatanPanenApiService: CatatanPanenService
) : CatatanPanenRepository {

    override suspend fun insertCatatanPanen(catatanPanen: CatatanPanen) {
        catatanPanenApiService.insertCatatanPanen(catatanPanen)
    }

    override suspend fun updateCatatanPanen(idPanen: String, catatanPanen: CatatanPanen) {
        catatanPanenApiService.updateCatatanPanen(idPanen, catatanPanen)
    }

    override suspend fun deleteCatatanPanen(idPanen: String) {
        try {
            val response = catatanPanenApiService.deleteCatatanPanen(idPanen)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete Catatan Panen. HTTP Status code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getCatatanPanen(): List<CatatanPanen> =
        catatanPanenApiService.getAllCatatanPanen()

    override suspend fun getCatatanPanenById(idPanen: String): CatatanPanen {
        return catatanPanenApiService.getCatatanPanenById(idPanen)
    }
}
