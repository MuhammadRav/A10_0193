package com.example.finalproject.ui.viewModel.pekerjaViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalproject.model.Pekerja
import com.example.finalproject.repository.PekerjaRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState{
    data class Success(val pekerja: List<Pekerja>): HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class PekerjaHomeViewModel (private val pkj: PekerjaRepository): ViewModel(){
    var pkjUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getPekerja()
    }

    fun getPekerja(){
        viewModelScope.launch {
            pkjUiState = HomeUiState.Loading
            pkjUiState = try {
                HomeUiState.Success(pkj.getPekerja().data)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }

    fun deletePekerja(id_pekerja:String){
        viewModelScope.launch {
            try {
                pkj.deletePekerja(id_pekerja)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}