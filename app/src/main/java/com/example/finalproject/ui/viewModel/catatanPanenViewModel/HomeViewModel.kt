package com.example.finalproject.ui.viewModel.catatanPanenViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalproject.model.CatatanPanen
import com.example.finalproject.repository.CatatanPanenRepository

import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState{
    data class Success(val catatanPanen: List<CatatanPanen>): HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class CatatanPanenHomeViewModel (private val ctp: CatatanPanenRepository): ViewModel(){
    var ctpUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getCatatanPanen()
    }

    fun getCatatanPanen(){
        viewModelScope.launch {
            ctpUiState = HomeUiState.Loading
            ctpUiState = try {
                HomeUiState.Success(ctp.getCatatanPanen().data)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }

    fun deleteCatatanPanen(id_panen:String){
        viewModelScope.launch {
            try {
                ctp.deleteCatatanPanen(id_panen)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}