package com.example.finalproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalproject.ui.view.Utama
import com.example.finalproject.ui.view.pekerjaView.PekerjaDetailScreen
import com.example.finalproject.ui.view.pekerjaView.PekerjaHomeScreen
import com.example.finalproject.ui.view.pekerjaView.PekerjaInsertScreen
import com.example.finalproject.ui.view.pekerjaView.PekerjaUpdateScreen
import com.example.finalproject.ui.view.tanamanView.TanamanDetailScreen
import com.example.finalproject.ui.view.tanamanView.TanamanHomeScreen
import com.example.finalproject.ui.view.tanamanView.TanamanInsertScreen
import com.example.finalproject.ui.view.tanamanView.TanamanUpdateScreen

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
    ){
    NavHost(
        navController=navController,
        startDestination = Utama.route,
        modifier = Modifier,
    ){
        composable(
            route = Utama.route
        ) {
            Utama(
                onTanamanButton = {
                    navController.navigate(AlamatHomeTanaman.route)
                },
                onPekerjaButton = {
                    navController.navigate(AlamatHomePekerja.route)
                },
                onAktivitasButton = {
                    navController.navigate(AlamatHomeAktivitas.route)
                },
                onCatatanButton = {
                    navController.navigate(AlamatHomeCatatan.route)
                }
            )
        }
        composable(
            route = AlamatHomeTanaman.route
        ){
            TanamanHomeScreen(
                onDetailClick = { idTanaman ->
                    navController.navigate("${AlamatDetailTanaman.route}/$idTanaman")
                    println(
                        "Pengelola Halaman: ID_TANAMAN = $idTanaman"
                    )
                },
                onAddTanaman = {
                    navController.navigate(AlamatInsertTanaman.route)
                },
                modifier = modifier
            )
        }
        composable(
            route = AlamatInsertTanaman.route
        ){
            TanamanInsertScreen(navigateBack = {
                navController.navigate(AlamatHomeTanaman.route){
                    popUpTo(AlamatHomeTanaman.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(AlamatDetailTanaman.routesWithArg,
            arguments = listOf(navArgument(AlamatDetailTanaman.ID_TANAMAN) {
            type = NavType.StringType })
        ){
            val idTanaman = it.arguments?.getString(AlamatDetailTanaman.ID_TANAMAN)
            idTanaman?.let { idTanaman ->
                TanamanDetailScreen(
                    onUpdateButton = { navController.navigate("${AlamatDetailTanaman.route}/$idTanaman") },
                    navigateBack = { navController.navigate(AlamatHomeTanaman.route) {
                        popUpTo(AlamatHomeTanaman.route) { inclusive = true }
                    }
                    }
                )
            }
        }
        composable(AlamatUpdateTanaman.routesWithArg,
                arguments = listOf(navArgument(AlamatDetailTanaman.ID_TANAMAN){
            type = NavType.StringType })
        ){
            val idTanaman = it.arguments?.getString(AlamatUpdateTanaman.ID_TANAMAN)
            idTanaman?.let { idTanaman ->
                TanamanUpdateScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
        // pekerja

        composable(
            route = AlamatHomePekerja.route
        ){
            PekerjaHomeScreen(
                onDetailClick = { idPekerja ->
                    navController.navigate("${AlamatDetailPekerja.route}/$idPekerja")
                    println(
                        "Pengelola Halaman: ID_PEKERJA = $idPekerja"
                    )
                },
                onAddPekerja = {
                    navController.navigate(AlamatInsertPekerja.route)
                },
                modifier = modifier
            )
        }
        composable(
            route = AlamatInsertPekerja.route
        ){
            PekerjaInsertScreen(navigateBack = {
                navController.navigate(AlamatHomePekerja.route){
                    popUpTo(AlamatHomePekerja.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(AlamatDetailPekerja.routesWithArg,
            arguments = listOf(navArgument(AlamatDetailPekerja.ID_PEKERJA) {
                type = NavType.StringType })
        ){
            val idPekerja = it.arguments?.getString(AlamatDetailPekerja.ID_PEKERJA)
            idPekerja?.let { idPekerja ->
                PekerjaDetailScreen(
                    onUpdateButton = { navController.navigate("${AlamatDetailPekerja.route}/$idPekerja") },
                    navigateBack = { navController.navigate(AlamatHomePekerja.route) {
                        popUpTo(AlamatHomePekerja.route) { inclusive = true }
                    }
                    }
                )
            }
        }
        composable(AlamatUpdatePekerja.routesWithArg,
            arguments = listOf(navArgument(AlamatDetailPekerja.ID_PEKERJA){
                type = NavType.StringType })
        ){
            val idPekerja = it.arguments?.getString(AlamatUpdatePekerja.ID_PEKERJA)
            idPekerja?.let { idPekerja ->
                PekerjaUpdateScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
    }
}