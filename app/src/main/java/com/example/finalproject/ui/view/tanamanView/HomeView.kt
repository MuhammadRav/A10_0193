package com.example.finalproject.ui.view.tanamanView

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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.R
import com.example.finalproject.customWidget.CstTopAppBar
import com.example.finalproject.model.Tanaman
import com.example.finalproject.navigation.AlamatHomeTanaman
import com.example.finalproject.ui.viewModel.tanamanViewModel.HomeUiState
import com.example.finalproject.ui.viewModel.tanamanViewModel.PenyediaTanamanViewModel
import com.example.finalproject.ui.viewModel.tanamanViewModel.TanamanHomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: TanamanHomeViewModel = viewModel(factory = PenyediaTanamanViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = AlamatHomeTanaman.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getTanaman()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Tanaman")
            }
        }
    ) { innerPadding ->
        HomeStatus(
            homeUiState = viewModel.tanamanUIState,
            retryAction = { viewModel.getTanaman() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteTanaman(it.idTanaman)
                viewModel.getTanaman()
            }
        )
    }
}

@Composable
fun HomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Tanaman) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (homeUiState) {
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeUiState.Success -> {
            if (homeUiState.tanaman.isEmpty()) {
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Tanaman")
                }
            } else {
                TanamanLayout(
                    tanaman = homeUiState.tanaman,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { onDetailClick(it.idTanaman) },
                    onDeleteClick = { onDeleteClick(it) }
                )
            }
        }

        is HomeUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

// Menampilkan pesan loading saat data sedang dimuat
@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.op),
        contentDescription = stringResource(R.string.loading)
    )
}

// Menampilkan pesan error jika gagal memuat data
@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.op), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun TanamanLayout(
    tanaman: List<Tanaman>,
    modifier: Modifier = Modifier,
    onDetailClick: (Tanaman) -> Unit,
    onDeleteClick: (Tanaman) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(tanaman) { tanaman ->
            TanamanCard(
                tanaman = tanaman,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onDetailClick(tanaman) },
                onDeleteClick = {
                    onDeleteClick(tanaman) }
            )
        }
    }
}

@Composable
fun TanamanCard(
    tanaman: Tanaman,
    modifier: Modifier = Modifier,
    onDeleteClick: (Tanaman) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tanaman.namaTanaman,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(tanaman) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
                Text(
                    text = tanaman.idTanaman,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = tanaman.periodeTanam,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = tanaman.deskripsiTanaman,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
