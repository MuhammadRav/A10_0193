package com.example.finalproject.service

import com.example.finalproject.model.AktivitasPertanian
import com.example.finalproject.model.AktivitasPertanianDetailResponse
import com.example.finalproject.model.AllAktivitasPertanianResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AktivitasPertanianService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("aktivitaspertanian/")
    suspend fun getAllAktivitas(): AllAktivitasPertanianResponse

    @GET("aktivitaspertanian/{id_aktivitas}")
    suspend fun getAktivitasById(@Path("id_aktivitas") id_aktivitas: String): AktivitasPertanianDetailResponse

    @POST("aktivitaspertanian/aktivitaspertanian_store")
    suspend fun insertAktivitas(@Body aktivitasPertanian: AktivitasPertanian)

    @PUT("aktivitaspertanian/{id_aktivitas}")
    suspend fun updateAktivitas(@Path("id_aktivitas") id_aktivitas: String, @Body aktivitasPertanian: AktivitasPertanian)

    @DELETE("aktivitaspertanian/{id_aktivitas}")
    suspend fun deleteAktivitas(@Path("id_aktivitas") id_aktivitas: String): retrofit2.Response<Void>
}