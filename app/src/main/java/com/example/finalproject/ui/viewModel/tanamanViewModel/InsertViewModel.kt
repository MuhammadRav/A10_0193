package com.example.finalproject.ui.viewModel.tanamanViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Tanaman
import com.example.finalproject.repository.TanamanRepository
import kotlinx.coroutines.launch

class TanamanInsertViewModel (private val tanamanRepository: TanamanRepository): ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun tanamanUpdateInsertState(insertUiEvent:InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun inserttanamanRepository() {
        viewModelScope.launch{
            try {
                tanamanRepository.insertTanaman(uiState.insertUiEvent.toTanaman())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val idTanaman: String = "",
    val namaTanaman: String = "",
    val periodeTanam: String = "",
    val deskripsiTanaman: String = "",
)

fun InsertUiEvent.toTanaman(): Tanaman = Tanaman(
    idTanaman = idTanaman,
    namaTanaman = namaTanaman,
    periodeTanam = periodeTanam,
    deskripsiTanaman = deskripsiTanaman
)

fun Tanaman.toUiStateTanaman(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Tanaman.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idTanaman = idTanaman,
    namaTanaman = namaTanaman,
    periodeTanam = periodeTanam,
    deskripsiTanaman = deskripsiTanaman
)
