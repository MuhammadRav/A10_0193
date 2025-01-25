package com.example.finalproject.ui.viewModel.tanamanViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalproject.model.Tanaman
import com.example.finalproject.repository.TanamanRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeUiState{
    data class Success(val tanaman: List<Tanaman>): HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class TanamanHomeViewModel (private val tnm: TanamanRepository): ViewModel(){
    var tnmUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getTanaman()
    }

    fun getTanaman(){
        viewModelScope.launch {
            tnmUiState = HomeUiState.Loading
            tnmUiState = try {
                HomeUiState.Success(tnm.getTanaman().data)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }

    fun deleteTanaman(id_tanaman:String){
        viewModelScope.launch {
            try {
                tnm.deleteTanaman(id_tanaman)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}