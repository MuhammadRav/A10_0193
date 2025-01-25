package com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalproject.model.AktivitasPertanian
import com.example.finalproject.repository.AktivitasPertanianRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState{
    data class Success(val aktivitasPertanian: List<AktivitasPertanian>): HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class AktivitasPertanianHomeViewModel (
    private val akp: AktivitasPertanianRepository
): ViewModel(){
    var akpUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getAktivitasPertanian()
    }

    fun getAktivitasPertanian(){
        viewModelScope.launch {
            akpUiState = HomeUiState.Loading
            akpUiState = try {
                HomeUiState.Success(akp.getAktivitasPertanian().data)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }

    fun deleteAktivitasPertanian(id_aktivitas:String){
        viewModelScope.launch {
            try {
                akp.deleteAktivitasPertanian(id_aktivitas)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}