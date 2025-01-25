package com.example.finalproject.ui.viewModel.catatanPanenViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.CatatanPanen
import com.example.finalproject.repository.CatatanPanenRepository
import kotlinx.coroutines.launch

class CatatanPanenInsertViewModel(private val ctp: CatatanPanenRepository): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertCatatanPanenState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertCatatanPanen(){
        viewModelScope.launch {
            try {
                ctp.insertCatatanPanen(uiState.insertUiEvent.toCatatanPanen())
            }catch (e:Exception){
                e.printStackTrace()
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
    keterangan = keterangan,

    )

fun CatatanPanen.toUiStateCatatanPanen(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun CatatanPanen.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_panen = id_panen,
    id_tanaman = id_tanaman,
    tanggal_panen = tanggal_panen,
    jumlah_panen = jumlah_panen,
    keterangan = keterangan,
)
