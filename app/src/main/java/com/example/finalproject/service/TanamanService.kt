package com.example.finalproject.service

import com.example.finalproject.model.AllTanamanResponse
import com.example.finalproject.model.Tanaman
import com.example.finalproject.model.TanamanDetailResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface TanamanService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("/tanaman")
    suspend fun getAllTanaman(): AllTanamanResponse

    @GET("/tanaman/{id_tanaman}")
    suspend fun getTanamanById(@Query("id_tanaman") idTanaman: String): TanamanDetailResponse

    @POST("/tanaman_store")
    suspend fun insertTanaman(@Body tanaman: Tanaman)

    @PUT("/tanaman_store/{id_tanaman}")
    suspend fun updateTanaman(@Query("id_tanaman") idTanaman: String, @Body tanaman: Tanaman)

    @DELETE("/tanaman_store/{id_tanaman}")
    suspend fun deleteTanaman(@Query("id_tanaman") idTanaman: String): retrofit2.Response<Void>
}
