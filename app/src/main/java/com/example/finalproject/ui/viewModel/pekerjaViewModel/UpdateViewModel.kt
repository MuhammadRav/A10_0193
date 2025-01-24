package com.example.finalproject.ui.viewModel.pekerjaViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.navigation.AlamatUpdatePekerja
import com.example.finalproject.repository.PekerjaRepository
import kotlinx.coroutines.launch

class PekerjaUpdateViewModel(
    savedStateHandle: SavedStateHandle,
    private val pkj: PekerjaRepository
) : ViewModel() {
    var UpdateUiState by mutableStateOf(InsertUiState())
        private set

    private val _idPekerja: String = checkNotNull(savedStateHandle[AlamatUpdatePekerja.ID_PEKERJA])

    init {
        viewModelScope.launch {
            UpdateUiState = pkj.getPekerjaById(_idPekerja)
                .toUiStatePekerja()
        }
    }
    fun updateInsertPekerjaState(insertUiEvent: InsertUiEvent){
        UpdateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updatePekerja(){
        viewModelScope.launch {
            try {
                pkj.updatePekerja(_idPekerja, UpdateUiState.insertUiEvent.toPekerja())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}