package com.example.finalproject.dependenciesInjection

import com.example.finalproject.repository.AktivitasPertanianRepository
import com.example.finalproject.repository.CatatanPanenRepository
import com.example.finalproject.repository.NetworkAktivitasPertanianRepository
import com.example.finalproject.repository.NetworkCatatanPanenRepository
import com.example.finalproject.repository.NetworkPekerjaRepository
import com.example.finalproject.repository.NetworkTanamanRepository
import com.example.finalproject.repository.PekerjaRepository
import com.example.finalproject.repository.TanamanRepository
import com.example.finalproject.service.AktivitasPertanianService
import com.example.finalproject.service.CatatanPanenService
import com.example.finalproject.service.PekerjaService
import com.example.finalproject.service.TanamanService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val tanamanRepository: TanamanRepository
    val pekerjaRepository: PekerjaRepository
    val catatanPanenRepository: CatatanPanenRepository
    val aktivitasPertanianRepository: AktivitasPertanianRepository
}

class PerkebunanContainer : AppContainer {
    private val baseUrl = "http://10.0.2.2/api/"
    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    // Tanaman API service dan repository
    private val tanamanService: TanamanService by lazy {
        retrofit.create(TanamanService::class.java)
    }
    override val tanamanRepository: TanamanRepository by lazy {
        NetworkTanamanRepository(tanamanService)
    }

    // Pekerja API service dan repository
    private val pekerjaService: PekerjaService by lazy {
        retrofit.create(PekerjaService::class.java)
    }
    override val pekerjaRepository: PekerjaRepository by lazy {
        NetworkPekerjaRepository(pekerjaService)
    }

    // CatatanPanen API service dan repository
    private val catatanPanenService: CatatanPanenService by lazy {
        retrofit.create(CatatanPanenService::class.java)
    }
    override val catatanPanenRepository: CatatanPanenRepository by lazy {
        NetworkCatatanPanenRepository(catatanPanenService)
    }

    // AktivitasPertanian API service dan repository
    private val aktivitasPertanianService: AktivitasPertanianService by lazy {
        retrofit.create(AktivitasPertanianService::class.java)
    }
    override val aktivitasPertanianRepository: AktivitasPertanianRepository by lazy {
        NetworkAktivitasPertanianRepository(aktivitasPertanianService)
    }
}
