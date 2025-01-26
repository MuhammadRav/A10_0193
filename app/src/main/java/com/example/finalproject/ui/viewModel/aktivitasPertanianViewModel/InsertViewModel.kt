package com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.AktivitasPertanian
import com.example.finalproject.model.Pekerja
import com.example.finalproject.model.Tanaman
import com.example.finalproject.repository.AktivitasPertanianRepository
import com.example.finalproject.repository.PekerjaRepository
import com.example.finalproject.repository.TanamanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AktivitasPertanianInsertViewModel(
    private val akp: AktivitasPertanianRepository,
    private val tnm: TanamanRepository,
    private val pkj: PekerjaRepository,
): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set

    private val _tanamanList = MutableStateFlow<List<Tanaman>>(emptyList())
    val tanamanList: StateFlow<List<Tanaman>> = _tanamanList

    private val _pekerjaList = MutableStateFlow<List<Pekerja>>(emptyList())
    val pekerjaList: StateFlow<List<Pekerja>> = _pekerjaList

    init {
        getTanaman()
        getPekerja()
    }

    fun updateInsertAktivitasPertanianState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertAktivitasPertanian(){
        viewModelScope.launch {
            try {
                akp.insertAktivitasPertanian(uiState.insertUiEvent.toAktivitasPertanian())
            }catch (e:Exception){
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
    private fun getPekerja() {
        viewModelScope.launch {
            try {
                val pkjData = pkj.getPekerja()
                _pekerjaList.value = pkjData.data
            } catch (e: Exception) {
                _pekerjaList.value = emptyList()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent(),
)

data class InsertUiEvent(
    val id_aktivitas: String = "",
    val id_tanaman: String = "",
    val id_pekerja: String = "",
    val tanggal_aktivitas: String = "",
    val deskripsi_aktivitas: String = "",
)

fun InsertUiEvent.toAktivitasPertanian(): AktivitasPertanian = AktivitasPertanian(
    id_aktivitas = id_aktivitas,
    id_tanaman = id_tanaman,
    id_pekerja = id_pekerja,
    tanggal_aktivitas = tanggal_aktivitas,
    deskripsi_aktivitas = deskripsi_aktivitas,

    )

fun AktivitasPertanian.toUiStateAktivitasPertanian(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun AktivitasPertanian.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_aktivitas = id_aktivitas,
    id_tanaman = id_tanaman,
    id_pekerja = id_pekerja,
    tanggal_aktivitas = tanggal_aktivitas,
    deskripsi_aktivitas = deskripsi_aktivitas,
)
