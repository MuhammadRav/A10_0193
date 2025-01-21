package com.example.finalproject.ui.viewModel.tanamanViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject.PerkebunanApplications

object PenyediaTanamanViewModel {
    val Factory = viewModelFactory {
        initializer {
            TanamanHomeViewModel(
                aplikasiTanaman().container.tanamanRepository
            )
        }

        initializer {
            TanamanInsertViewModel(
                aplikasiTanaman().container.tanamanRepository
            )
        }

        initializer {
            TanamanDetailViewModel(
                createSavedStateHandle(),
                aplikasiTanaman().container.tanamanRepository
            )
        }

        initializer {
            TanamanUpdateViewModel(
                createSavedStateHandle(),
                aplikasiTanaman().container.tanamanRepository
            )
        }
    }

    // Function to get PerkebunanApplications
    fun CreationExtras.aplikasiTanaman(): PerkebunanApplications =
        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PerkebunanApplications
}
