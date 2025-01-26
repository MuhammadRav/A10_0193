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
import com.example.finalproject.ui.view.aktivitasPertanianView.AktivitasPertanianDetailScreen
import com.example.finalproject.ui.view.aktivitasPertanianView.AktivitasPertanianHomeScreen
import com.example.finalproject.ui.view.aktivitasPertanianView.AktivitasPertanianInsertScreen
import com.example.finalproject.ui.view.aktivitasPertanianView.AktivitasPertanianUpdateScreen
import com.example.finalproject.ui.view.catatanPanenView.CatatanPanenDetailScreen
import com.example.finalproject.ui.view.catatanPanenView.CatatanPanenHomeScreen
import com.example.finalproject.ui.view.catatanPanenView.CatatanPanenInsertScreen
import com.example.finalproject.ui.view.catatanPanenView.CatatanPanenUpdateScreen
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
        startDestination = AlamatHomeTanaman.route,
        modifier = Modifier,
    ){
        composable(
            route = Utama.route
        ) {
            Utama(
                onPekerjaButton = {
                    navController.navigate(AlamatHomePekerja.route)
                },
                onAktivitasButton = {
                    navController.navigate(AlamatHomeAktivitas.route)
                },
            )
        }
        composable(
            route = AlamatHomeTanaman.route
        ){
            TanamanHomeScreen(
                onDetailClick = { id_tanaman ->
                    navController.navigate("${AlamatDetailTanaman.route}/$id_tanaman")
                    println(
                        "Pengelola Halaman: ID_TANAMAN = $id_tanaman"
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
        composable(
            route = AlamatDetailTanaman.routesWithArg,
            arguments = listOf(navArgument(AlamatDetailTanaman.ID_TANAMAN) { type = NavType.StringType })
        ) { backStackEntry ->
            val idTanaman = backStackEntry.arguments?.getString(AlamatDetailTanaman.ID_TANAMAN)
            idTanaman?.let {
                TanamanDetailScreen(
                    onUpdateButton = { navController.navigate("${AlamatUpdateTanaman.route}/$idTanaman") },
                    navigateBack = {
                        navController.navigate(AlamatHomeTanaman.route) {
                            popUpTo(AlamatHomeTanaman.route) { inclusive = true }
                        }
                    },
                    onPanenButton = {
                        navController.navigate(AlamatHomeCatatan.route) // Navigasi ke catatan panen
                    },
                    onDetailLebihLanjutButton = {
                        navController.navigate(Utama.route) // Navigasi ke catatan panen
                    }
                )
            }
        }
        composable(AlamatUpdateTanaman.routesWithArg,
                arguments = listOf(navArgument(AlamatDetailTanaman.ID_TANAMAN) {
                    type = NavType.StringType })
        ){
            val id_tanaman = it.arguments?.getString(AlamatUpdateTanaman.ID_TANAMAN)
            id_tanaman?.let { id_tanaman ->
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
                onDetailClick = { id_pekerja ->
                    navController.navigate("${AlamatDetailPekerja.route}/$id_pekerja")
                    println(
                        "Pengelola Halaman: ID_PEKERJA = $id_pekerja"
                    )
                },
                onAddPekerja = {
                    navController.navigate(AlamatInsertPekerja.route)
                },
                navigateBack = {
                    navController.navigate(Utama.route){
                        popUpTo(Utama.route){
                            inclusive = true
                        }
                    }
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
            val id_pekerja = it.arguments?.getString(AlamatDetailPekerja.ID_PEKERJA)
            id_pekerja?.let { id_pekerja ->
                PekerjaDetailScreen(
                    onUpdateButton = { navController.navigate("${AlamatUpdatePekerja.route}/$id_pekerja") },
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
            val id_pekerja = it.arguments?.getString(AlamatUpdatePekerja.ID_PEKERJA)
            id_pekerja?.let { id_pekerja ->
                PekerjaUpdateScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }

        // Catatan Panen
        composable(
            route = AlamatHomeCatatan.route
        ){
            CatatanPanenHomeScreen(
                onDetailClick = { id_panen ->
                    navController.navigate("${AlamatDetailCatatan.route}/$id_panen")
                    println(
                        "Pengelola Halaman: ID_PANEN = $id_panen"
                    )
                },
                onAddCatatan = {
                    navController.navigate(AlamatInsertCatatan.route)
                },
                navigateBack = {
                    navController.navigate(AlamatHomeTanaman.route){
                        popUpTo(AlamatHomeTanaman.route){
                            inclusive = true
                        }
                    }
                },
                modifier = modifier
            )
        }
        composable(
            route = AlamatInsertCatatan.route
        ){
            CatatanPanenInsertScreen(navigateBack = {
                navController.navigate(AlamatHomeCatatan.route){
                    popUpTo(AlamatHomeCatatan.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(AlamatDetailCatatan.routesWithArg,
            arguments = listOf(navArgument(AlamatDetailCatatan.ID_PANEN) {
                type = NavType.StringType })
        ){
            val id_panen = it.arguments?.getString(AlamatDetailCatatan.ID_PANEN)
            id_panen?.let { id_panen ->
                CatatanPanenDetailScreen(
                    onUpdateButton = { navController.navigate("${AlamatUpdateCatatan.route}/$id_panen") },
                    navigateBack = { navController.navigate(AlamatHomeCatatan.route) {
                        popUpTo(AlamatHomeCatatan.route) { inclusive = true }
                    }
                    }
                )
            }
        }
        composable(AlamatUpdateCatatan.routesWithArg,
            arguments = listOf(navArgument(AlamatDetailCatatan.ID_PANEN){
                type = NavType.StringType })
        ){
            val id_panen = it.arguments?.getString(AlamatUpdateCatatan.ID_PANEN)
            id_panen?.let { id_panen ->
                CatatanPanenUpdateScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }

        // Aktivitas Pertanian
        composable(
            route = AlamatHomeAktivitas.route
        ){
            AktivitasPertanianHomeScreen(
                onDetailClick = { id_aktivitas ->
                    navController.navigate("${AlamatDetailAktivitas.route}/$id_aktivitas")
                    println(
                        "Pengelola Halaman: ID_AKTIVITAS = $id_aktivitas"
                    )
                },
                onAddAktivitas = {
                    navController.navigate(AlamatInsertAktivitas.route)
                },
                navigateBack = {
                    navController.navigate(Utama.route){
                        popUpTo(Utama.route){
                            inclusive = true
                        }
                    }
                },
                modifier = modifier
            )
        }
        composable(
            route = AlamatInsertAktivitas.route
        ){
            AktivitasPertanianInsertScreen(navigateBack = {
                navController.navigate(AlamatHomeAktivitas.route){
                    popUpTo(AlamatHomeAktivitas.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(AlamatDetailAktivitas.routesWithArg,
            arguments = listOf(navArgument(AlamatDetailAktivitas.ID_AKTIVITAS) {
                type = NavType.StringType })
        ){
            val id_aktivitas = it.arguments?.getString(AlamatDetailAktivitas.ID_AKTIVITAS)
            id_aktivitas?.let { id_aktivitas ->
                AktivitasPertanianDetailScreen(
                    onUpdateButton = { navController.navigate("${AlamatUpdateAktivitas.route}/$id_aktivitas") },
                    navigateBack = { navController.navigate(AlamatHomeAktivitas.route) {
                        popUpTo(AlamatHomeAktivitas.route) { inclusive = true }
                    }
                    }
                )
            }
        }
        composable(AlamatUpdateAktivitas.routesWithArg,
            arguments = listOf(navArgument(AlamatDetailAktivitas.ID_AKTIVITAS){
                type = NavType.StringType })
        ){
            val id_aktivitas = it.arguments?.getString(AlamatUpdateAktivitas.ID_AKTIVITAS)
            id_aktivitas?.let { id_aktivitas ->
                AktivitasPertanianUpdateScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
    }
}