package com.example.finalproject.ui.view.tanamanView

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
import com.example.finalproject.model.Tanaman
import com.example.finalproject.navigation.AlamatDetailTanaman
import com.example.finalproject.ui.viewModel.tanamanViewModel.DetailUiState
import com.example.finalproject.ui.viewModel.tanamanViewModel.PenyediaTanamanViewModel
import com.example.finalproject.ui.viewModel.tanamanViewModel.TanamanDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TanamanDetailScreen(
    navigateBack: () -> Unit,
    onUpdateButton: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TanamanDetailViewModel = viewModel(factory = PenyediaTanamanViewModel.Factory)
) {
    Scaffold(
        topBar = {
            CstTopAppBar(
                title = AlamatDetailTanaman.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {
                    viewModel.getTanamanById()
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
                    contentDescription = "Edit Tanaman"
                )
            }
        }
    ) { innerPadding ->
        TanamanDetailStatus(
            modifier = Modifier.padding(innerPadding),
            detailUiState = viewModel.tanamanDetailState,
            retryAction = { viewModel.getTanamanById() },
            onDeleteClick = {
                viewModel.deleteTanaman(viewModel.tanamanDetailState.let { state ->
                    if (state is DetailUiState.Success) state.tanaman.idTanaman else ""
                })
                navigateBack()
            }
        )
    }
}


@Composable
fun TanamanDetailStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState,
    onDeleteClick: () -> Unit
) {
    when (detailUiState) {
        is DetailUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is DetailUiState.Success -> {
            if (detailUiState.tanaman.idTanaman.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) { Text("Data tidak ditemukan") }
            } else {
                ItemDetailTanaman(
                    tanaman = detailUiState.tanaman,
                    modifier = modifier.fillMaxWidth(),
                    onDeleteClick = onDeleteClick
                )
            }
        }
        is DetailUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}


@Composable
fun ItemDetailTanaman(
    modifier: Modifier = Modifier,
    tanaman: Tanaman,
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
            ComponentDetailTanaman(judul = "ID Tanaman", isinya = tanaman.idTanaman)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailTanaman(judul = "Nama Tanaman", isinya = tanaman.namaTanaman)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailTanaman(judul = "Periode Tanam", isinya = tanaman.periodeTanam)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailTanaman(judul = "Deskripsi", isinya = tanaman.deskripsiTanaman)
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
fun ComponentDetailTanaman(
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