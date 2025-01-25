package com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Tanaman
import com.example.finalproject.navigation.AlamatUpdateAktivitas
import com.example.finalproject.repository.AktivitasPertanianRepository
import com.example.finalproject.repository.TanamanRepository
import kotlinx.coroutines.launch

class AktivitasPertanianUpdateViewModel(
    savedStateHandle: SavedStateHandle,
    private val akp: AktivitasPertanianRepository,
//    private val tnm: TanamanRepository
) : ViewModel() {
    var UpdateUiState by mutableStateOf(InsertUiState())
        private set
//    var tnmlist by mutableStateOf<List<Tanaman>>(emptyList())
//        private set

    private val _id_aktivitas: String = checkNotNull(savedStateHandle[AlamatUpdateAktivitas.ID_AKTIVITAS])

    init {
        viewModelScope.launch {
            UpdateUiState = akp.getAktivitasPertanianById(_id_aktivitas)
                .toUiStateAktivitasPertanian()
//            getTanaman()
        }
    }
    fun updateInsertAktivitasPertanianState(insertUiEvent: InsertUiEvent){
        UpdateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    fun updateAktivitasPertanian(){
        viewModelScope.launch {
            try {
                akp.updateAktivitasPertanian(_id_aktivitas,
                    UpdateUiState.insertUiEvent.toAktivitasPertanian())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
//    private fun getTanaman() {
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