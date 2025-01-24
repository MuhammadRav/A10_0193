package com.example.finalproject.ui.viewModel.pekerjaViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalproject.model.Pekerja
import com.example.finalproject.navigation.AlamatDetailPekerja
import com.example.finalproject.repository.PekerjaRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailUiState {
    data class Success(val pekerja: Pekerja) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class PekerjaDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val pkj: PekerjaRepository
) : ViewModel() {

    var pekerjaDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _idPekerja: String = checkNotNull(savedStateHandle[AlamatDetailPekerja.ID_PEKERJA])

    init {
        getPekerjaById()
    }

    fun getPekerjaById() {
        viewModelScope.launch {
            pekerjaDetailState = DetailUiState.Loading
            pekerjaDetailState = try {
                val pekerja = pkj.getPekerjaById(_idPekerja)
                DetailUiState.Success(pekerja)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    fun deletePekerja(idPekerja:String) {
        viewModelScope.launch {
            try {
                pkj.deletePekerja(idPekerja)
            }catch (e:IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}
