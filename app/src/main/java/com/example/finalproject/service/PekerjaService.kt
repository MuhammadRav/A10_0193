package com.example.finalproject.service

import com.example.finalproject.model.Pekerja
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PekerjaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("bacaperkebunan.php")
    suspend fun getAllPekerja(): List<Pekerja>

    @GET("baca1perkebunan.php")
    suspend fun getPekerjaById(@Query("id_pekerja") idPekerja: String): Pekerja

    @POST("insertperkebunan.php")
    suspend fun insertPekerja(@Body pekerja: Pekerja)

    @PUT("editperkebunan.php/{id_pekerja}")
    suspend fun updatePekerja(@Query("id_pekerja") idPekerja: String, @Body pekerja: Pekerja)

    @DELETE("deleteperkebunan.php/{id_pekerja}")
    suspend fun deletePekerja(@Query("id_pekerja") idPekerja: String): retrofit2.Response<Void>
}