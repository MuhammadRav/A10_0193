package com.example.finalproject.ui.viewModel.tanamanViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.navigation.AlamatUpdateTanaman
import com.example.finalproject.repository.TanamanRepository
import kotlinx.coroutines.launch

class TanamanUpdateViewModel(
    savedStateHandle: SavedStateHandle,
    private val tnm: TanamanRepository
) : ViewModel() {
    var UpdateUiState by mutableStateOf(InsertUiState())
        private set

    private val _id_tanaman: String = checkNotNull(savedStateHandle[AlamatUpdateTanaman.ID_TANAMAN])

    init {
        viewModelScope.launch {
            UpdateUiState = tnm.getTanamanById(_id_tanaman)
                .toUiStateTanaman()
        }
    }
    fun updateInsertTanamanState(insertUiEvent: InsertUiEvent){
        UpdateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

     fun updateTanaman(){
        viewModelScope.launch {
            try {
                tnm.updateTanaman(_id_tanaman, UpdateUiState.insertUiEvent.toTanaman())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
