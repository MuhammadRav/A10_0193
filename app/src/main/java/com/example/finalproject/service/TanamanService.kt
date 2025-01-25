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
import retrofit2.http.Path

interface TanamanService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("tanaman/")
    suspend fun getAllTanaman(): AllTanamanResponse

    @GET("tanaman/{id_tanaman}")
    suspend fun getTanamanById(@Path("id_tanaman") id_tanaman: String): TanamanDetailResponse

    @POST("tanaman/tanaman_store")
    suspend fun insertTanaman(@Body tanaman: Tanaman)

    @PUT("tanaman/{id_tanaman}")
    suspend fun updateTanaman(@Path("id_tanaman") id_tanaman: String, @Body tanaman: Tanaman)

    @DELETE("tanaman/{id_tanaman}")
    suspend fun deleteTanaman(@Path("id_tanaman") id_tanaman: String): retrofit2.Response<Void>
}
