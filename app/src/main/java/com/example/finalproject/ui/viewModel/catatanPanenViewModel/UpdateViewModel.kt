package com.example.finalproject.ui.viewModel.catatanPanenViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Tanaman
import com.example.finalproject.navigation.AlamatUpdateCatatan
import com.example.finalproject.repository.CatatanPanenRepository
import com.example.finalproject.repository.TanamanRepository
import kotlinx.coroutines.launch

class CatatanPanenUpdateViewModel(
    savedStateHandle: SavedStateHandle,
    private val ctp: CatatanPanenRepository,
//    private val tnm: TanamanRepository
) : ViewModel() {
    var UpdateUiState by mutableStateOf(InsertUiState())
        private set
//    var tnmlist by mutableStateOf<List<Tanaman>>(emptyList())
//        private set

    private val _id_panen: String = checkNotNull(savedStateHandle[AlamatUpdateCatatan.ID_PANEN])

    init {
        viewModelScope.launch {
            UpdateUiState = ctp.getCatatanPanenById(_id_panen)
                .toUiStateCatatanPanen()
//            getTanaman()
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