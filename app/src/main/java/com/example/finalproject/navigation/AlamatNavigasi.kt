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

// Pekerja
object AlamatInsertPekerja : AlamatNavigasi {
    override val route = "insert_pekerja"
    override val titleRes = "Insert Pekerja"
}

object AlamatUpdatePekerja : AlamatNavigasi {
    override val route = "update_pekerja"
    override val titleRes = "Update Pekerja"
    const val ID_PEKERJA = "id_pekerja"
    val routesWithArg = "$route/{$ID_PEKERJA}"
}

object AlamatDetailPekerja : AlamatNavigasi {
    override val route = "detail_pekerja"
    override val titleRes = "Detail Pekerja"
    const val ID_PEKERJA = "id_pekerja"
    val routesWithArg = "$route/{$ID_PEKERJA}"
}

object AlamatHomePekerja : AlamatNavigasi {
    override val route = "home_pekerja"
    override val titleRes = "Home Pekerja"
}

// Aktivitas
object AlamatInsertAktivitas : AlamatNavigasi {
    override val route = "insert_aktivitas"
    override val titleRes = "Insert Aktivitas"
}

object AlamatUpdateAktivitas : AlamatNavigasi {
    override val route = "update_aktivitas"
    override val titleRes = "Update Aktivitas"
    const val ID_AKTIVITAS = "id_aktivitas"
    val routesWithArg = "$route/{$ID_AKTIVITAS}"
}

object AlamatDetailAktivitas : AlamatNavigasi {
    override val route = "detail_aktivitas"
    override val titleRes = "Detail Aktivitas"
    const val ID_AKTIVITAS = "id_aktivitas"
    val routesWithArg = "$route/{${ID_AKTIVITAS}}"
}

object AlamatHomeAktivitas : AlamatNavigasi {
    override val route = "home_aktivitas"
    override val titleRes = "Home Aktivitas"
}

// Catatan
object AlamatInsertCatatan : AlamatNavigasi {
    override val route = "insert_catatan"
    override val titleRes = "Insert Catatan"
}

object AlamatUpdateCatatan : AlamatNavigasi {
    override val route = "update_catatan"
    override val titleRes = "Update Catatan"
    const val ID_PANEN = "id_panen"
    val routesWithArg = "$route/{$ID_PANEN}"
}

object AlamatDetailCatatan : AlamatNavigasi {
    override val route = "detail_catatan"
    override val titleRes = "Detail Catatan"
    const val ID_PANEN = "id_panen"
    val routesWithArg = "$route/{${ID_PANEN}}"
}

object AlamatHomeCatatan : AlamatNavigasi {
    override val route = "home_panen"
    override val titleRes = "Home Panen"
}

object Utama : AlamatNavigasi {
    override val route = "utama"
    override val titleRes = "Halaman Utama"
}