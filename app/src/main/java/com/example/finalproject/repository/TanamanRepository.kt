package com.example.finalproject.repository

import com.example.finalproject.model.AllTanamanResponse
import com.example.finalproject.model.Tanaman
import com.example.finalproject.service.TanamanService
import okio.IOException

interface TanamanRepository {
    suspend fun getTanaman():AllTanamanResponse
    suspend fun insertTanaman(tanaman: Tanaman)
    suspend fun updateTanaman(id_tanaman: String, tanaman: Tanaman)
    suspend fun deleteTanaman(id_tanaman: String)
    suspend fun getTanamanById(id_tanaman: String): Tanaman
}

class NetworkTanamanRepository(
    private val tanamanApiService: TanamanService
) : TanamanRepository {

    override suspend fun insertTanaman(tanaman: Tanaman) {
        tanamanApiService.insertTanaman(tanaman)
    }

    override suspend fun updateTanaman(id_tanaman: String, tanaman: Tanaman) {
        tanamanApiService.updateTanaman(id_tanaman, tanaman)
    }

    override suspend fun deleteTanaman(id_tanaman: String) {
        try {
            val response = tanamanApiService.deleteTanaman(id_tanaman)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete Tanaman. HTTP Status code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getTanaman(): AllTanamanResponse =
        tanamanApiService.getAllTanaman()

    override suspend fun getTanamanById(id_tanaman: String): Tanaman {
        return tanamanApiService.getTanamanById(id_tanaman).data
    }
}
