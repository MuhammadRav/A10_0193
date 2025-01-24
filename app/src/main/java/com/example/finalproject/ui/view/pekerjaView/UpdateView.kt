package com.example.finalproject.ui.view.pekerjaView

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
import com.example.finalproject.navigation.AlamatUpdatePekerja
import com.example.finalproject.ui.viewModel.pekerjaViewModel.PekerjaUpdateViewModel
import com.example.finalproject.ui.viewModel.pekerjaViewModel.PenyediaPekerjaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PekerjaUpdateScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: ()-> Unit,
    viewModel: PekerjaUpdateViewModel = viewModel(factory = PenyediaPekerjaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = AlamatUpdatePekerja.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ){ padding ->
        InsertBody(
            modifier = Modifier.padding(padding),
            insertUiState = viewModel.UpdateUiState,
            onPekerjaValueChange = viewModel::updateInsertPekerjaState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePekerja()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            }
        )
    }
}