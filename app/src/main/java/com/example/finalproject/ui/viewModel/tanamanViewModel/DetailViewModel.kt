package com.example.finalproject.ui.viewModel.tanamanViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalproject.model.Tanaman
import com.example.finalproject.navigation.AlamatDetailTanaman
import com.example.finalproject.repository.TanamanRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailUiState {
    data class Success(val tanaman: Tanaman) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class TanamanDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val tanamanRepository: TanamanRepository
) : ViewModel() {

    var tanamanDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _idTanaman: String = checkNotNull(savedStateHandle[AlamatDetailTanaman.ID_TANAMAN])

    init {
        getTanamanById()
    }

    fun getTanamanById() {
        viewModelScope.launch {
            tanamanDetailState = DetailUiState.Loading
            tanamanDetailState = try {
                val tanaman = tanamanRepository.getTanamanById(_idTanaman)
                DetailUiState.Success(tanaman)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    fun deleteTanaman(id: String) {
        viewModelScope.launch {
            try {
                tanamanRepository.deleteTanaman(id)
            } catch (e: IOException) {
                tanamanDetailState = DetailUiState.Error
            } catch (e: HttpException) {
                tanamanDetailState = DetailUiState.Error
            }
        }
    }
}
