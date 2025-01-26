package com.example.finalproject.ui.viewModel.catatanPanenViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.CatatanPanen
import com.example.finalproject.model.Tanaman
import com.example.finalproject.repository.CatatanPanenRepository
import com.example.finalproject.repository.TanamanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatatanPanenInsertViewModel(
    private val ctp: CatatanPanenRepository,
    private val tnm: TanamanRepository
) : ViewModel() {
    // State untuk UI form
    var uiState by mutableStateOf(InsertUiState())
        private set

    // State untuk daftar tanaman (menggunakan StateFlow)
    private val _tanamanList = MutableStateFlow<List<Tanaman>>(emptyList())
    val tanamanList: StateFlow<List<Tanaman>> = _tanamanList

    // Inisialisasi dengan mengambil data tanaman
    init {
        getTanaman()
    }

    // Memperbarui state UI berdasarkan input pengguna
    fun updateInsertCatatanPanenState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    // Fungsi untuk menambahkan catatan panen
    fun insertCatatanPanen() {
        viewModelScope.launch {
            try {
                ctp.insertCatatanPanen(uiState.insertUiEvent.toCatatanPanen())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Fungsi untuk mengambil data tanaman dari repository
    private fun getTanaman() {
        viewModelScope.launch {
            try {
                val tnmData = tnm.getTanaman()
                _tanamanList.value = tnmData.data
            } catch (e: Exception) {
                _tanamanList.value = emptyList()
            }
        }
    }
}

// Data class untuk state UI form
data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

// Data class untuk event input pengguna
data class InsertUiEvent(
    val id_panen: String = "",
    val id_tanaman: String = "",
    val tanggal_panen: String = "",
    val jumlah_panen: String = "",
    val keterangan: String = "",
)

// Fungsi untuk mengonversi InsertUiEvent menjadi model CatatanPanen
fun InsertUiEvent.toCatatanPanen(): CatatanPanen = CatatanPanen(
    id_panen = id_panen,
    id_tanaman = id_tanaman,
    tanggal_panen = tanggal_panen,
    jumlah_panen = jumlah_panen,
    keterangan = keterangan
)

// Fungsi untuk mengonversi CatatanPanen menjadi InsertUiState
fun CatatanPanen.toUiStateCatatanPanen(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

// Fungsi untuk mengonversi CatatanPanen menjadi InsertUiEvent
fun CatatanPanen.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_panen = id_panen,
    id_tanaman = id_tanaman,
    tanggal_panen = tanggal_panen,
    jumlah_panen = jumlah_panen,
    keterangan = keterangan
)
