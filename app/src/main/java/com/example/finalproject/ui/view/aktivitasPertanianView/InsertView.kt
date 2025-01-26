package com.example.finalproject.ui.view.aktivitasPertanianView

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.customWidget.CstTopAppBar
import com.example.finalproject.model.Pekerja
import com.example.finalproject.model.Tanaman
import com.example.finalproject.navigation.AlamatInsertAktivitas
import com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel.AktivitasPertanianInsertViewModel
import com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel.InsertUiEvent
import com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel.InsertUiState
import com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel.PenyediaAktivitasPertanianViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AktivitasPertanianInsertScreen(
    navigateBack: ()-> Unit,
    modifier: Modifier = Modifier,
    viewModel: AktivitasPertanianInsertViewModel = viewModel(factory = PenyediaAktivitasPertanianViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val tanamanList by viewModel.tanamanList.collectAsState()
    val pekerjaList by viewModel.pekerjaList.collectAsState()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = AlamatInsertAktivitas.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ){ innerPadding ->
        InsertBody(
            insertUiState = viewModel.uiState,
            tanamanList = tanamanList,
            pekerjaList = pekerjaList,
            onAktivitasPertanianValueChange = viewModel::updateInsertAktivitasPertanianState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertAktivitasPertanian()
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
    tanamanList: List<Tanaman>,
    pekerjaList: List<Pekerja>,
    onAktivitasPertanianValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: ()->Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInput(
            insertUiEvent = insertUiState.insertUiEvent,
            tanamanList = tanamanList,
            pekerjaList = pekerjaList,
            onValueChange = onAktivitasPertanianValueChange,
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
    tanamanList: List<Tanaman>,
    pekerjaList: List<Pekerja>,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent)->Unit={},
    enabled: Boolean = true
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = insertUiEvent.id_aktivitas,
            onValueChange = { onValueChange(insertUiEvent.copy(id_aktivitas = it))},
            label = { Text("ID Aktivitas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
//        OutlinedTextField(
//            value = insertUiEvent.id_tanaman,
//            onValueChange = { onValueChange(insertUiEvent.copy(id_tanaman = it))},
//            label = { Text("ID Tanaman") },
//            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
//            singleLine = true
//        )
        DropdownMenuTanaman(
            selectedTanaman = insertUiEvent.id_tanaman,
            tnmList = tanamanList,
            onItemSelected = { selectedTanaman ->
                onValueChange(insertUiEvent.copy(id_tanaman = selectedTanaman))
            },
            enabled = enabled
        )
//        OutlinedTextField(
//            value = insertUiEvent.id_pekerja,
//            onValueChange = {onValueChange(insertUiEvent.copy(id_pekerja = it))},
//            label = { Text("ID Pekrja") },
//            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
//            singleLine = true
//        )
        DropdownMenuPekerja(
            selectedPekerja = insertUiEvent.id_pekerja,
            pkjList = pekerjaList,
            onItemSelected = { selectedPekerja ->
                onValueChange(insertUiEvent.copy(id_pekerja = selectedPekerja))
            },
            enabled = enabled
        )
        OutlinedTextField(
            value = insertUiEvent.tanggal_aktivitas,
            onValueChange = { onValueChange(insertUiEvent.copy(tanggal_aktivitas = it))},
            label = { Text("Tanggal Aktivitas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.deskripsi_aktivitas,
            onValueChange = { onValueChange(insertUiEvent.copy(deskripsi_aktivitas = it))},
            label = { Text("Deskripsi Aktivitas") },
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

@Composable
fun DropdownMenuTanaman(
    selectedTanaman: String,
    tnmList: List<Tanaman>,
    onItemSelected: (String) -> Unit,
    enabled: Boolean
) {
    var expanded by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = selectedTanaman,
        onValueChange = { },
        label = { Text("Pilih ID Tanaman") },
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = true,
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
            }
        }
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        tnmList.forEach { tanaman ->
            DropdownMenuItem(
                text = { Text(tanaman.id_tanaman) },
                onClick = {
                    onItemSelected(tanaman.id_tanaman)
                    expanded = false
                }
            )
        }
    }
}

@Composable
fun DropdownMenuPekerja(
    selectedPekerja: String,
    pkjList: List<Pekerja>,
    onItemSelected: (String) -> Unit,
    enabled: Boolean
) {
    var expanded by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = selectedPekerja,
        onValueChange = { },
        label = { Text("Pilih ID Pekerja") },
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = true,
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
            }
        }
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        pkjList.forEach { tanaman ->
            DropdownMenuItem(
                text = { Text(tanaman.id_pekerja) },
                onClick = {
                    onItemSelected(tanaman.id_pekerja)
                    expanded = false
                }
            )
        }
    }
}