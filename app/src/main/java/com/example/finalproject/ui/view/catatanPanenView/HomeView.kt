package com.example.finalproject.ui.view.catatanPanenView

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
import com.example.finalproject.model.CatatanPanen
import com.example.finalproject.navigation.AlamatHomeCatatan
import com.example.finalproject.ui.viewModel.catatanPanenViewModel.CatatanPanenHomeViewModel
import com.example.finalproject.ui.viewModel.catatanPanenViewModel.HomeUiState
import com.example.finalproject.ui.viewModel.catatanPanenViewModel.PenyediaCatatanPanenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatatanPanenHomeScreen(
    onAddCatatan: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: CatatanPanenHomeViewModel = viewModel(factory = PenyediaCatatanPanenViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = AlamatHomeCatatan.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getCatatanPanen()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddCatatan,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "Add Catatan")
            }
        },
    ){ innerPadding ->
        CatatanPanenHomeStatus(
            homeUiState = viewModel.ctpUiState,
            retryAction = { viewModel.getCatatanPanen()},
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteCatatanPanen(it.id_panen)
                viewModel.getCatatanPanen()
            }
        )
    }
}

@Composable
fun CatatanPanenHomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (CatatanPanen) -> Unit = {},
    onDetailClick: (String) -> Unit
){
    when(homeUiState){
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeUiState.Success ->
            if (homeUiState.catatanPanen.isEmpty()){
                return Box(modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center){
                    Text("Tidak ada data Catatan Panen")
                }
            } else{
                CatatanPanenLayout(
                    catatanPanen = homeUiState.catatanPanen,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_panen)
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
        painter = painterResource(R.drawable.op),
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
            painter = painterResource(id = R.drawable.op),
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
fun CatatanPanenLayout(
    catatanPanen: List<CatatanPanen>,
    modifier: Modifier = Modifier,
    onDetailClick: (CatatanPanen) -> Unit,
    onDeleteClick: (CatatanPanen) -> Unit = {}
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ){
        items(catatanPanen){
                catatanPanen ->
            CatatanPanenCard(
                catatanPanen = catatanPanen,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(catatanPanen) },
                onDeleteClick = {
                    onDeleteClick(catatanPanen)
                }
            )
        }
    }
}

@Composable
fun CatatanPanenCard(
    catatanPanen: CatatanPanen,
    modifier: Modifier = Modifier,
    onDeleteClick: (CatatanPanen) -> Unit
){
    Card (
        modifier = modifier.padding(bottom = 20.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = catatanPanen.id_panen,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(catatanPanen) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
                Text(
                    text = catatanPanen.id_tanaman,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Text(
                text = catatanPanen.tanggal_panen,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = catatanPanen.jumlah_panen,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = catatanPanen.keterangan,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}