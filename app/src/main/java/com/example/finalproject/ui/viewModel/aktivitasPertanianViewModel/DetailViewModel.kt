package com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalproject.model.AktivitasPertanian
import com.example.finalproject.navigation.AlamatDetailAktivitas
import com.example.finalproject.repository.AktivitasPertanianRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailUiState {
    data class Success(val aktivitasPertanian: AktivitasPertanian) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class AktivitasPertanianDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val akp: AktivitasPertanianRepository
) : ViewModel() {
    var aktivitasPertanianDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _id_aktivitas: String = checkNotNull(savedStateHandle[AlamatDetailAktivitas.ID_AKTIVITAS])

    init {
        getAktivitasPertanianById()
    }

    fun getAktivitasPertanianById() {
        viewModelScope.launch {
            aktivitasPertanianDetailState = DetailUiState.Loading
            aktivitasPertanianDetailState = try {
                val aktivitasPertanian = akp.getAktivitasPertanianById(_id_aktivitas)
                DetailUiState.Success(aktivitasPertanian)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    fun deleteAktivitasPertanian(id_aktivitas:String) {
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