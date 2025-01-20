package com.example.finalproject.service

import com.example.finalproject.model.Tanaman
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

    @GET("bacaperkebunan.php")
    suspend fun getAllTanaman(): List<Tanaman>

    @GET("baca1perkebunan.php")
    suspend fun getTanamanById(@Query("id_tanaman") idTanaman: String): Tanaman

    @POST("insertperkebunan.php")
    suspend fun insertTanaman(@Body tanaman: Tanaman)

    @PUT("editperkebunan.php/{id_tanaman}")
    suspend fun updateTanaman(@Query("id_tanaman") idTanaman: String, @Body tanaman: Tanaman)

    @DELETE("deleteperkebunan.php/{id_tanaman}")
    suspend fun deleteTanaman(@Query("id_tanaman") idTanaman: String): retrofit2.Response<Void>
}
