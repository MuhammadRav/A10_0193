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

    @GET("catatanpanen")
    suspend fun getAllCatatanPanen(): AllCatatanPanenResponse

    @GET("catatanpanen/{id_panen}")
    suspend fun getCatatanPanenById(@Path("id_panen") idPanen: String): CatatanPanenDetailResponse

    @POST("catatanpanen_store")
    suspend fun insertCatatanPanen(@Body catatanPanen: CatatanPanen)

    @PUT("catatanpanen_store/{id_panen}")
    suspend fun updateCatatanPanen(@Path("id_panen") idPanen: String, @Body catatanPanen: CatatanPanen)

    @DELETE("catatanpanen_store/{id_panen}")
    suspend fun deleteCatatanPanen(@Path("id_panen") idPanen: String): retrofit2.Response<Void>
}