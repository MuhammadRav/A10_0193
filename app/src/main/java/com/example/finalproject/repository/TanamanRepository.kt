package com.example.finalproject.repository

import com.example.finalproject.model.Tanaman
import com.example.finalproject.service.TanamanService
import okio.IOException

interface TanamanRepository {
    suspend fun getTanaman(): List<Tanaman>
    suspend fun insertTanaman(tanaman: Tanaman)
    suspend fun updateTanaman(idTanaman: String, tanaman: Tanaman)
    suspend fun deleteTanaman(idTanaman: String)
    suspend fun getTanamanById(idTanaman: String): Tanaman
}

class NetworkTanamanRepository(
    private val tanamanApiService: TanamanService
) : TanamanRepository {

    override suspend fun insertTanaman(tanaman: Tanaman) {
        tanamanApiService.insertTanaman(tanaman)
    }

    override suspend fun updateTanaman(idTanaman: String, tanaman: Tanaman) {
        tanamanApiService.updateTanaman(idTanaman, tanaman)
    }

    override suspend fun deleteTanaman(idTanaman: String) {
        try {
            val response = tanamanApiService.deleteTanaman(idTanaman)
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

    override suspend fun getTanaman(): List<Tanaman> =
        tanamanApiService.getAllTanaman()

    override suspend fun getTanamanById(idTanaman: String): Tanaman {
        return tanamanApiService.getTanamanById(idTanaman)
    }
}
