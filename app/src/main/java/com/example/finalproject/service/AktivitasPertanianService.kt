package com.example.finalproject.service

import com.example.finalproject.model.AktivitasPertanian
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface AktivitasPertanianService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("bacaaktivitas.php")
    suspend fun getAllAktivitas(): List<AktivitasPertanian>

    @GET("baca1aktivitas.php")
    suspend fun getAktivitasById(@Query("id_aktivitas") idAktivitas: Int): AktivitasPertanian

    @POST("insertaktivitas.php")
    suspend fun insertAktivitas(@Body aktivitasPertanian: AktivitasPertanian)

    @PUT("editaktivitas.php/{id_aktivitas}")
    suspend fun updateAktivitas(@Query("id_aktivitas") idAktivitas: Int, @Body aktivitasPertanian: AktivitasPertanian)

    @DELETE("deleteaktivitas.php/{id_aktivitas}")
    suspend fun deleteAktivitas(@Query("id_aktivitas") idAktivitas: Int): retrofit2.Response<Void>
}