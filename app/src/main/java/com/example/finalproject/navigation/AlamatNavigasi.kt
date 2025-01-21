package com.example.finalproject.navigation

interface AlamatNavigasi {
    val route: String
    val titleRes: String
}

// Tanaman
object AlamatInsertTanaman : AlamatNavigasi {
    override val route = "insert_tanaman"
    override val titleRes = "Insert Tanaman"
}

object AlamatUpdateTanaman : AlamatNavigasi {
    override val route = "update_tanaman"
    override val titleRes = "Update Tanaman"
    const val ID_TANAMAN = "id_tanaman"
    val routesWithArg = "$route/{$ID_TANAMAN}"
}

object AlamatDetailTanaman : AlamatNavigasi {
    override val route = "detail_tanaman"
    override val titleRes = "Detail Tanaman"
    const val ID_TANAMAN = "id_tanaman"
    val routesWithArg = "$route/{$ID_TANAMAN}"
}

object AlamatHomeTanaman : AlamatNavigasi {
    override val route = "home_tanaman"
    override val titleRes = "Home Tanaman"
}
