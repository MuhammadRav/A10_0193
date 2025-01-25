package com.example.finalproject.ui.viewModel.catatanPanenViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.navigation.AlamatUpdateCatatan
import com.example.finalproject.repository.CatatanPanenRepository
import kotlinx.coroutines.launch

class CatatanPanenUpdateViewModel(
    savedStateHandle: SavedStateHandle,
    private val ctp: CatatanPanenRepository
) : ViewModel() {
    var UpdateUiState by mutableStateOf(InsertUiState())
        private set

    private val _id_panen: String = checkNotNull(savedStateHandle[AlamatUpdateCatatan.ID_PANEN])

    init {
        viewModelScope.launch {
            UpdateUiState = ctp.getCatatanPanenById(_id_panen)
                .toUiStateCatatanPanen()
        }
    }
    fun updateInsertCatatanPanenState(insertUiEvent: InsertUiEvent){
        UpdateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    fun updateCatatanPanen(){
        viewModelScope.launch {
            try {
                ctp.updateCatatanPanen(_id_panen, UpdateUiState.insertUiEvent.toCatatanPanen())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}