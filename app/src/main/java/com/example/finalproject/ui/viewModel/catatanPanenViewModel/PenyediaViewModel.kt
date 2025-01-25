package com.example.finalproject.ui.viewModel.catatanPanenViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject.PerkebunanApplications


object PenyediaCatatanPanenViewModel {
    val Factory = viewModelFactory {
        initializer {
            CatatanPanenHomeViewModel(
                aplikasiCatatanPanen().container.catatanPanenRepository
            )
        }

        initializer {
            CatatanPanenInsertViewModel(
                aplikasiCatatanPanen().container.catatanPanenRepository
            )
        }

        initializer {
            CatatanPanenDetailViewModel(
                createSavedStateHandle(),
                aplikasiCatatanPanen().container.catatanPanenRepository
            )
        }

        initializer {
            CatatanPanenUpdateViewModel(
                createSavedStateHandle(),
                aplikasiCatatanPanen().container.catatanPanenRepository
            )
        }
    }

    // Function to get PerkebunanApplications
    fun CreationExtras.aplikasiCatatanPanen(): PerkebunanApplications =
        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PerkebunanApplications
}