package com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.AktivitasPertanian
import com.example.finalproject.model.Tanaman
import com.example.finalproject.repository.AktivitasPertanianRepository
import com.example.finalproject.repository.TanamanRepository
import kotlinx.coroutines.launch

class AktivitasPertanianInsertViewModel(
    private val akp: AktivitasPertanianRepository,
//    private val tnm: TanamanRepository,
): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set
//    var tnmlist by mutableStateOf<List<Tanaman>>(emptyList())
//        private set

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
//    fun getTanaman() {
//        viewModelScope.launch {
//            try {
//                val tnmData = tnm.getTanaman()
//                tnmlist = tnmData.data
//            } catch (e: Exception) {
//                tnmlist = emptyList()
//            }
//        }
//    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent(),
//    val tnmList: List<Tanaman> = emptyList()
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
