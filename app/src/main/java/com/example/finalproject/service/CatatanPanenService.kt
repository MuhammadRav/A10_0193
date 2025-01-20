package com.example.finalproject.service

import com.example.finalproject.model.CatatanPanen
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface CatatanPanenService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("bacaperkebunan.php")
    suspend fun getAllCatatanPanen(): List<CatatanPanen>

    @GET("baca1perkebunan.php")
    suspend fun getCatatanPanenById(@Query("id_panen") idPanen: String): CatatanPanen

    @POST("insertperkebunan.php")
    suspend fun insertCatatanPanen(@Body catatanPanen: CatatanPanen)

    @PUT("editperkebunan.php/{id_panen}")
    suspend fun updateCatatanPanen(@Query("id_panen") idPanen: String, @Body catatanPanen: CatatanPanen)

    @DELETE("deleteperkebunan.php/{id_panen}")
    suspend fun deleteCatatanPanen(@Query("id_panen") idPanen: String): retrofit2.Response<Void>
}