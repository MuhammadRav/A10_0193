package com.example.finalproject.ui.view.tanamanView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.customWidget.CstTopAppBar
import com.example.finalproject.navigation.AlamatInsertTanaman
import com.example.finalproject.ui.viewModel.tanamanViewModel.InsertUiEvent
import com.example.finalproject.ui.viewModel.tanamanViewModel.InsertUiState
import com.example.finalproject.ui.viewModel.tanamanViewModel.PenyediaTanamanViewModel
import com.example.finalproject.ui.viewModel.tanamanViewModel.TanamanInsertViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TanamanInsertScreen(
    navigateBack: ()-> Unit,
    modifier: Modifier = Modifier,
    viewModel: TanamanInsertViewModel  = viewModel(factory = PenyediaTanamanViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = AlamatInsertTanaman.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ){ innerPadding ->
        InsertBody(
            insertUiState = viewModel.uiState,
            onTanamanValueChange = viewModel::updateInsertTanamanState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertTanaman()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun InsertBody(
    insertUiState: InsertUiState,
    onTanamanValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: ()->Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInput(
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onTanamanValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertUiEvent: InsertUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent)->Unit={},
    enabled: Boolean = true
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = insertUiEvent.namaTanaman,
            onValueChange = { onValueChange(insertUiEvent.copy(namaTanaman = it))},
            label = { Text("Nama Tanaman") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.idTanaman,
            onValueChange = { onValueChange(insertUiEvent.copy(idTanaman = it))},
            label = { Text("ID Tanaman") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.periodeTanam,
            onValueChange = {onValueChange(insertUiEvent.copy(periodeTanam = it))},
            label = { Text("Periode Tanam") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.deskripsiTanaman,
            onValueChange = { onValueChange(insertUiEvent.copy(deskripsiTanaman = it))},
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled){
            Text(
                text = "Isi Semua Data",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}