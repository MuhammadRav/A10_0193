package com.example.finalproject.ui.viewModel.catatanPanenViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalproject.model.CatatanPanen
import com.example.finalproject.navigation.AlamatDetailCatatan
import com.example.finalproject.repository.CatatanPanenRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailUiState {
    data class Success(val catatanPanen: CatatanPanen) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class CatatanPanenDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val ctp: CatatanPanenRepository
) : ViewModel() {
    var catatanPanenDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _id_panen: String = checkNotNull(savedStateHandle[AlamatDetailCatatan.ID_PANEN])

    init {
        getCatatanPanenById()
    }

    fun getCatatanPanenById() {
        viewModelScope.launch {
            catatanPanenDetailState = DetailUiState.Loading
            catatanPanenDetailState = try {
                val catatanPanen = ctp.getCatatanPanenById(_id_panen)
                DetailUiState.Success(catatanPanen)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    fun deleteCatatanPanen(id_panen:String) {
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