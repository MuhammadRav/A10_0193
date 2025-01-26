package com.example.finalproject.ui.view.catatanPanenView

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.customWidget.CstTopAppBar
import com.example.finalproject.navigation.AlamatUpdateCatatan
import com.example.finalproject.ui.viewModel.catatanPanenViewModel.CatatanPanenUpdateViewModel
import com.example.finalproject.ui.viewModel.catatanPanenViewModel.PenyediaCatatanPanenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatatanPanenUpdateScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: CatatanPanenUpdateViewModel = viewModel(factory = PenyediaCatatanPanenViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    // Menggunakan collectAsState untuk mendapatkan nilai tanamanList secara reaktif
    val tanamanList = viewModel.tanamanList.collectAsState().value

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = AlamatUpdateCatatan.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ) { padding ->
        // Pastikan tanamanList diteruskan ke InsertBody
        InsertBody(
            modifier = Modifier.padding(padding),
            insertUiState = viewModel.UpdateUiState,
            onCatatanPanenValueChange = viewModel::updateInsertCatatanPanenState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateCatatanPanen()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            },
            tanamanList = tanamanList // Meneruskan tanamanList yang sudah di-observe
        )
    }
}
