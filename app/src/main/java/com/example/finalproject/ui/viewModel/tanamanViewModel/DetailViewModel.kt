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
    private val tnm: TanamanRepository
) : ViewModel() {

    var detailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _idTanaman: String = checkNotNull(savedStateHandle[AlamatDetailTanaman.ID_TANAMAN])

    init {
        getTanamanById()
    }

    fun getTanamanById() {
        viewModelScope.launch {
            detailState = DetailUiState.Loading
            detailState = try {
                val tanaman = tnm.getTanamanById(_idTanaman)
                DetailUiState.Success(tanaman)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    fun deleteTanaman(idTanaman:String) {
        viewModelScope.launch {
            try {
                tnm.deleteTanaman(idTanaman)
            }catch (e:IOException){
                HomeUiState.Error
            }catch (e:HttpException){
                HomeUiState.Error
            }
        }
    }
}
