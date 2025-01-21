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

sealed class HomeUiState {
    data class Success(val tanaman: List<Tanaman>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class TanamanHomeViewModel(private val tanamanRepository: TanamanRepository) : ViewModel() {

    var tanamanUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getTanaman()
    }

    fun getTanaman() {
        viewModelScope.launch {
            tanamanUIState = HomeUiState.Loading
            tanamanUIState = try {
                HomeUiState.Success(tanamanRepository.getTanaman())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun deleteTanaman(id: String) {
        viewModelScope.launch {
            try {
                tanamanRepository.deleteTanaman(id)
                getTanaman()
            } catch (e: IOException) {
                tanamanUIState = HomeUiState.Error
            } catch (e: HttpException) {
                tanamanUIState = HomeUiState.Error
            }
        }
    }
}

