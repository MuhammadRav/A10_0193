package com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject.PerkebunanApplications

object PenyediaAktivitasPertanianViewModel {
    val Factory = viewModelFactory {
        initializer {
            AktivitasPertanianHomeViewModel(
                aplikasiAktivitasPertanian().container.aktivitasPertanianRepository
            )
        }

        initializer {
            AktivitasPertanianInsertViewModel(
                aplikasiAktivitasPertanian().container.aktivitasPertanianRepository,
                aplikasiAktivitasPertanian().container.tanamanRepository,
                aplikasiAktivitasPertanian().container.pekerjaRepository


            )
        }

        initializer {
            AktivitasPertanianDetailViewModel(
                createSavedStateHandle(),
                aplikasiAktivitasPertanian().container.aktivitasPertanianRepository
            )
        }

        initializer {
            AktivitasPertanianUpdateViewModel(
                createSavedStateHandle(),
                aplikasiAktivitasPertanian().container.aktivitasPertanianRepository,
                aplikasiAktivitasPertanian().container.tanamanRepository,
                aplikasiAktivitasPertanian().container.pekerjaRepository
            )
        }
    }

    // Function to get PerkebunanApplications
    fun CreationExtras.aplikasiAktivitasPertanian(): PerkebunanApplications =
        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PerkebunanApplications
}