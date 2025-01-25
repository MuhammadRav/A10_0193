package com.example.finalproject.repository

import com.example.finalproject.model.AllCatatanPanenResponse
import com.example.finalproject.model.CatatanPanen
import com.example.finalproject.service.CatatanPanenService
import okio.IOException

interface CatatanPanenRepository {
    suspend fun getCatatanPanen(): AllCatatanPanenResponse
    suspend fun insertCatatanPanen(catatanPanen: CatatanPanen)
    suspend fun updateCatatanPanen(id_panen: String, catatanPanen: CatatanPanen)
    suspend fun deleteCatatanPanen(id_panen: String)
    suspend fun getCatatanPanenById(id_panen: String): CatatanPanen
}

class NetworkCatatanPanenRepository(
    private val catatanPanenApiService: CatatanPanenService
) : CatatanPanenRepository {

    override suspend fun insertCatatanPanen(catatanPanen: CatatanPanen) {
        catatanPanenApiService.insertCatatanPanen(catatanPanen)
    }

    override suspend fun updateCatatanPanen(id_panen: String, catatanPanen: CatatanPanen) {
        catatanPanenApiService.updateCatatanPanen(id_panen, catatanPanen)
    }

    override suspend fun deleteCatatanPanen(id_panen: String) {
        try {
            val response = catatanPanenApiService.deleteCatatanPanen(id_panen)
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

    override suspend fun getCatatanPanen(): AllCatatanPanenResponse =
        catatanPanenApiService.getAllCatatanPanen()

    override suspend fun getCatatanPanenById(id_panen: String): CatatanPanen {
        return catatanPanenApiService.getCatatanPanenById(id_panen).data
    }
}
