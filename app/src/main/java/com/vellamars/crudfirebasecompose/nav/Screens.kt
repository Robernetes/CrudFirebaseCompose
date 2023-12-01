package com.vellamars.crudfirebasecompose.nav

sealed class Screens(val route: String){
    object MainScreen: Screens(route = "main_screen")
    object GetDataScreen: Screens(route = "get_data_screen")
    object AddDataScreen: Screens(route = "add_data_screen")
    object ListMedicines: Screens(route = "get_all_data_screen")
}
