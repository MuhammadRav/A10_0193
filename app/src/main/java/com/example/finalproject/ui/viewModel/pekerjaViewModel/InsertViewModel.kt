package com.example.finalproject.ui.viewModel.pekerjaViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Pekerja
import com.example.finalproject.repository.PekerjaRepository
import kotlinx.coroutines.launch

class PekerjaInsertViewModel(private val pkj: PekerjaRepository): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertPekerjaState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertPekerja(){
        viewModelScope.launch {
            try {
                pkj.insertPekerja(uiState.insertUiEvent.toPekerja())
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
    val idPekerja: String = "",
    val namaPekerja: String = "",
    val jabatan: String = "",
    val kontakPekerja: String = "",
)

fun InsertUiEvent.toPekerja(): Pekerja = Pekerja(
    id_pekerja = idPekerja,
    nama_pekerja = namaPekerja,
    jabatan = jabatan,
    kontak_pekerja = kontakPekerja,
)

fun Pekerja.toUiStatePekerja(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Pekerja.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idPekerja = id_pekerja,
    namaPekerja = nama_pekerja,
    jabatan = jabatan,
    kontakPekerja = kontak_pekerja,
)
