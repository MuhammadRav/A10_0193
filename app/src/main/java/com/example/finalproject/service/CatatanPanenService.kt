package com.example.finalproject.service

import com.example.finalproject.model.AllCatatanPanenResponse
import com.example.finalproject.model.CatatanPanen
import com.example.finalproject.model.CatatanPanenDetailResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CatatanPanenService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("catatanpanen/")
    suspend fun getAllCatatanPanen(): AllCatatanPanenResponse

    @GET("catatanpanen/{id_panen}")
    suspend fun getCatatanPanenById(@Path("id_panen") id_panen: String): CatatanPanenDetailResponse

    @POST("catatanpanen/catatanpanen_store")
    suspend fun insertCatatanPanen(@Body catatanPanen: CatatanPanen)

    @PUT("catatanpanen/{id_panen}")
    suspend fun updateCatatanPanen(@Path("id_panen") id_panen: String, @Body catatanPanen: CatatanPanen)

    @DELETE("catatanpanen/{id_panen}")
    suspend fun deleteCatatanPanen(@Path("id_panen") id_panen: String): retrofit2.Response<Void>
}