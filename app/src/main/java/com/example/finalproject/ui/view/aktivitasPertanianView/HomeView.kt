package com.example.finalproject.ui.view.aktivitasPertanianView

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.R
import com.example.finalproject.customWidget.CstTopAppBar
import com.example.finalproject.model.AktivitasPertanian
import com.example.finalproject.navigation.AlamatHomeAktivitas
import com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel.AktivitasPertanianHomeViewModel
import com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel.HomeUiState
import com.example.finalproject.ui.viewModel.aktivitasPertanianViewModel.PenyediaAktivitasPertanianViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AktivitasPertanianHomeScreen(
    navigateBack: () -> Unit,
    onAddAktivitas: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: AktivitasPertanianHomeViewModel = viewModel(factory = PenyediaAktivitasPertanianViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = AlamatHomeAktivitas.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getAktivitasPertanian()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddAktivitas,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "Add Aktivitas")
            }
        },
    ){ innerPadding ->
        AktivitasPertanianHomeStatus(
            homeUiState = viewModel.akpUiState,
            retryAction = { viewModel.getAktivitasPertanian() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteAktivitasPertanian(it.id_aktivitas)
                viewModel.getAktivitasPertanian()
            }
        )
    }
}

@Composable
fun AktivitasPertanianHomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (AktivitasPertanian) -> Unit = {},
    onDetailClick: (String) -> Unit
){
    when(homeUiState){
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeUiState.Success ->
            if (homeUiState.aktivitasPertanian.isEmpty()){
                return Box(modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center){
                    Text("Tidak ada data Aktivitas Pertanian")
                }
            } else{
                AktivitasPertanianLayout(
                    aktivitasPertanian = homeUiState.aktivitasPertanian,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_aktivitas)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(
    modifier: Modifier = Modifier
){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit,
            modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.loading),
            contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun AktivitasPertanianLayout(
    aktivitasPertanian: List<AktivitasPertanian>,
    modifier: Modifier = Modifier,
    onDetailClick: (AktivitasPertanian) -> Unit,
    onDeleteClick: (AktivitasPertanian) -> Unit = {}
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ){
        items(aktivitasPertanian){
                aktivitasPertanian ->
            AktivitasPertanianCard(
                aktivitasPertanian = aktivitasPertanian,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(aktivitasPertanian) },
                onDeleteClick = {
                    onDeleteClick(aktivitasPertanian)
                }
            )
        }
    }
}

@Composable
fun AktivitasPertanianCard(
    aktivitasPertanian: AktivitasPertanian,
    modifier: Modifier = Modifier,
    onDeleteClick: (AktivitasPertanian) -> Unit
) {
    Card(
        modifier = modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth()
            .height(170.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.card5),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = aktivitasPertanian.id_tanaman,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = { onDeleteClick(aktivitasPertanian) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                    Text(
                        text = aktivitasPertanian.id_aktivitas,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black
                    )
                }
                Text(
                    text = "ID Pekerja: ${aktivitasPertanian.id_pekerja}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Text(
                    text = "Tanggal Aktivitas: ${aktivitasPertanian.tanggal_aktivitas}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Text(
                    text = "Deskripsi: ${aktivitasPertanian.deskripsi_aktivitas}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
            }
        }
    }
}