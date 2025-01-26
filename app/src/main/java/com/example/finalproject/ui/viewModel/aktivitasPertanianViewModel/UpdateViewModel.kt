package com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Pekerja
import com.example.finalproject.model.Tanaman
import com.example.finalproject.navigation.AlamatUpdateAktivitas
import com.example.finalproject.repository.AktivitasPertanianRepository
import com.example.finalproject.repository.PekerjaRepository
import com.example.finalproject.repository.TanamanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AktivitasPertanianUpdateViewModel(
    savedStateHandle: SavedStateHandle,
    private val akp: AktivitasPertanianRepository,
    private val tnm: TanamanRepository,
    private val pkj: PekerjaRepository

) : ViewModel() {
    var UpdateUiState by mutableStateOf(InsertUiState())
        private set

    private val _tanamanList = MutableStateFlow<List<Tanaman>>(emptyList())
    val tanamanList: StateFlow<List<Tanaman>> get() = _tanamanList

    private val _pekerjaList = MutableStateFlow<List<Pekerja>>(emptyList())
    val pekerjaList: StateFlow<List<Pekerja>> get() = _pekerjaList

    private val _id_aktivitas: String = checkNotNull(savedStateHandle[AlamatUpdateAktivitas.ID_AKTIVITAS])

    init {
        viewModelScope.launch {
            UpdateUiState = akp.getAktivitasPertanianById(_id_aktivitas)
                .toUiStateAktivitasPertanian()
            getTanaman()
            getPekerja()
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