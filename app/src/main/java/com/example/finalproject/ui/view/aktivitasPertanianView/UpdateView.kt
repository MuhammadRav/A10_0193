package com.example.finalproject.ui.view.aktivitasPertanianView

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.customWidget.CstTopAppBar
import com.example.finalproject.navigation.AlamatUpdateAktivitas
import com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel.AktivitasPertanianUpdateViewModel
import com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel.PenyediaAktivitasPertanianViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AktivitasPertanianUpdateScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: ()-> Unit,
    viewModel: AktivitasPertanianUpdateViewModel = viewModel(factory = PenyediaAktivitasPertanianViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = AlamatUpdateAktivitas.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ){ padding ->
        InsertBody(
            modifier = Modifier.padding(padding),
            insertUiState = viewModel.UpdateUiState,
            onAktivitasPertanianValueChange = viewModel::updateInsertAktivitasPertanianState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateAktivitasPertanian()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            }
        )
    }
}