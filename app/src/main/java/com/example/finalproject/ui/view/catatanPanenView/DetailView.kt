package com.example.finalproject.ui.view.catatanPanenView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.customWidget.CstTopAppBar
import com.example.finalproject.model.CatatanPanen
import com.example.finalproject.navigation.AlamatDetailCatatan
import com.example.finalproject.ui.viewModel.catatanPanenViewModel.CatatanPanenDetailViewModel
import com.example.finalproject.ui.viewModel.catatanPanenViewModel.DetailUiState
import com.example.finalproject.ui.viewModel.catatanPanenViewModel.PenyediaCatatanPanenViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatatanPanenDetailScreen(
    navigateBack: () -> Unit,
    onUpdateButton: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CatatanPanenDetailViewModel = viewModel(factory = PenyediaCatatanPanenViewModel.Factory)
) {
    Scaffold(
        topBar = {
            CstTopAppBar(
                title = AlamatDetailCatatan.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {
                    viewModel.getCatatanPanenById()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onUpdateButton,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Catatan Panen"
                )
            }
        }
    ) { innerPadding ->
        CatatanPanenDetailStatus(
            modifier = Modifier.padding(innerPadding),
            detailUiState = viewModel.catatanPanenDetailState,
            retryAction = { viewModel.getCatatanPanenById() },
            onDeleteClick = {
                viewModel.deleteCatatanPanen(viewModel.catatanPanenDetailState.let { state ->
                    if (state is DetailUiState.Success) state.catatanPanen.id_panen else ""
                })
                navigateBack()
            }
        )
    }
}


@Composable
fun CatatanPanenDetailStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState,
    onDeleteClick: () -> Unit
) {
    when (detailUiState) {
        is DetailUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is DetailUiState.Success -> {
            if (detailUiState.catatanPanen.id_panen.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) { Text("Data tidak ditemukan") }
            } else {
                ItemDetailCatatanPanen(
                    catatanPanen = detailUiState.catatanPanen,
                    modifier = modifier.fillMaxWidth(),
                    onDeleteClick = onDeleteClick
                )
            }
        }
        is DetailUiState.Error -> OnError(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}


@Composable
fun ItemDetailCatatanPanen(
    modifier: Modifier = Modifier,
    catatanPanen: CatatanPanen,
    onDeleteClick: () -> Unit
) {
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    Card(
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailCatatanPanen(judul = "ID Catatan", isinya = catatanPanen.id_panen)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailCatatanPanen(judul = "ID Tanaman", isinya = catatanPanen.id_tanaman)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailCatatanPanen(judul = "Tanggal Panen", isinya = catatanPanen.tanggal_panen)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailCatatanPanen(judul = "Jumlah Panen", isinya = catatanPanen.jumlah_panen)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailCatatanPanen(judul = "Keterangan", isinya = catatanPanen.keterangan)
            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = {
                    deleteConfirmationRequired = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Delete")
            }

            if (deleteConfirmationRequired) {
                DeleteConfirmationDialog(
                    onDeleteConfirm = {
                        deleteConfirmationRequired = false
                        onDeleteClick()
                    },
                    onDeleteCancel = { deleteConfirmationRequired = false },
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun ComponentDetailCatatanPanen(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = judul,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = isinya,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
//            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = {},
        title = {
            Text("Delete Data")
        },
        text = {
            Text("Apakah anda yakin ingin menghapus data?")
        },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = onDeleteCancel
            ) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDeleteConfirm
            ) {
                Text(text = "Yes")
            }
        })
}