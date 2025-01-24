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
import com.example.finalproject.ui.view.tanamanView.TanamanHomeScreen

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
//        composable(
//            route = AlamatInsertTanaman.route
//        ){
//            TanamanInsertView(
//                onBack = {
//                    navController.popBackStack()
//                },
//                onNavigate = {
//                    navController.popBackStack()
//                },
//                modifier = modifier,
//            )
//        }
//        composable(
//            AlamatDetailTanaman.routesWithArg,
//            arguments = listOf(
//                navArgument(AlamatDetailTanaman.ID_TANAMAN){
//                    type = NavType.StringType
//                }
//            )
//        ){
//            val idTanaman = it.arguments?.getString(AlamatDetailTanaman.ID_TANAMAN)
//            idTanaman?.let { idTanaman ->
//                TanamanDetailView(
//                    onBack = {
//                        navController.popBackStack()
//                    },
//                )
//            }
//        }
//        composable(
//            AlamatUpdateTanaman.routesWithArg,
//            arguments = listOf(
//                navArgument(AlamatUpdateTanaman.ID_TANAMAN){
//                    type = NavType.StringType
//                }
//            )
//        ){
//            TanamanUpdateView(
//                onBack = {
//                    navController.popBackStack()
//                },
//                onNavigate = {
//                    navController.popBackStack()
//                },
//                modifier = modifier,
//            )
//        }
    }
}