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
    var uiState by mutableStateOf(InsertUiState())
        private set

    private val _tanamanList = MutableStateFlow<List<Tanaman>>(emptyList())
    val tanamanList: StateFlow<List<Tanaman>> = _tanamanList

    init {
        getTanaman()
    }

    fun updateInsertCatatanPanenState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    fun insertCatatanPanen() {
        viewModelScope.launch {
            try {
                ctp.insertCatatanPanen(uiState.insertUiEvent.toCatatanPanen())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

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

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val id_panen: String = "",
    val id_tanaman: String = "",
    val tanggal_panen: String = "",
    val jumlah_panen: String = "",
    val keterangan: String = "",
)

fun InsertUiEvent.toCatatanPanen(): CatatanPanen = CatatanPanen(
    id_panen = id_panen,
    id_tanaman = id_tanaman,
    tanggal_panen = tanggal_panen,
    jumlah_panen = jumlah_panen,
    keterangan = keterangan
)

fun CatatanPanen.toUiStateCatatanPanen(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun CatatanPanen.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_panen = id_panen,
    id_tanaman = id_tanaman,
    tanggal_panen = tanggal_panen,
    jumlah_panen = jumlah_panen,
    keterangan = keterangan
)
