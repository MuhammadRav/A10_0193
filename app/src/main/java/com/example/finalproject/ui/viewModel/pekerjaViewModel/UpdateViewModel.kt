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

    private val _id_pekerja: String = checkNotNull(savedStateHandle[AlamatUpdatePekerja.ID_PEKERJA])

    init {
        viewModelScope.launch {
            UpdateUiState = pkj.getPekerjaById(_id_pekerja)
                .toUiStatePekerja()
        }
    }
    fun updateInsertPekerjaState(insertUiEvent: InsertUiEvent){
        UpdateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    fun updatePekerja(){
        viewModelScope.launch {
            try {
                pkj.updatePekerja(_id_pekerja, UpdateUiState.insertUiEvent.toPekerja())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}