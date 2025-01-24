package com.example.finalproject.ui.viewModel.pekerjaViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject.PerkebunanApplications
import com.example.finalproject.ui.viewModel.tanamanViewModel.TanamanDetailViewModel
import com.example.finalproject.ui.viewModel.tanamanViewModel.TanamanHomeViewModel
import com.example.finalproject.ui.viewModel.tanamanViewModel.TanamanInsertViewModel
import com.example.finalproject.ui.viewModel.tanamanViewModel.TanamanUpdateViewModel

object PenyediaPekerjaViewModel {
    val Factory = viewModelFactory {
        initializer {
            PekerjaHomeViewModel(
                aplikasiPekerja().container.pekerjaRepository
            )
        }

        initializer {
            PekerjaInsertViewModel(
                aplikasiPekerja().container.pekerjaRepository
            )
        }

        initializer {
            PekerjaDetailViewModel(
                createSavedStateHandle(),
                aplikasiPekerja().container.pekerjaRepository
            )
        }

        initializer {
            PekerjaUpdateViewModel(
                createSavedStateHandle(),
                aplikasiPekerja().container.pekerjaRepository
            )
        }
    }

    // Function to get PerkebunanApplications
    fun CreationExtras.aplikasiPekerja(): PerkebunanApplications =
        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PerkebunanApplications
}
