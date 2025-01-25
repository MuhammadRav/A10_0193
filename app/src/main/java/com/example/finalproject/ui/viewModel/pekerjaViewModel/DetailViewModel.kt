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

    private val _id_pekerja: String = checkNotNull(savedStateHandle[AlamatDetailPekerja.ID_PEKERJA])

    init {
        getPekerjaById()
    }

    fun getPekerjaById() {
        viewModelScope.launch {
            pekerjaDetailState = DetailUiState.Loading
            pekerjaDetailState = try {
                val pekerja = pkj.getPekerjaById(_id_pekerja)
                DetailUiState.Success(pekerja)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    fun deletePekerja(id_pekerja:String) {
        viewModelScope.launch {
            try {
                pkj.deletePekerja(id_pekerja)
            }catch (e:IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}
