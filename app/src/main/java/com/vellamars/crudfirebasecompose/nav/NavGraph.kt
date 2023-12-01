package com.vellamars.crudfirebasecompose.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vellamars.crudfirebasecompose.screen.AddDataScreen
import com.vellamars.crudfirebasecompose.screen.GetDataScreen
import com.vellamars.crudfirebasecompose.screen.ListMedicines
import com.vellamars.crudfirebasecompose.screen.MainScreen
import com.vellamars.crudfirebasecompose.util.SharedViewModel

@Composable
fun NavGraph(navHostController: NavHostController, sharedViewModel: SharedViewModel){

    NavHost(navController = navHostController, startDestination = Screens.MainScreen.route){

        composable(route = Screens.MainScreen.route){

            MainScreen(navController = navHostController)
        }

        composable(route = Screens.GetDataScreen.route){

            GetDataScreen(navController = navHostController, sharedViewModel = sharedViewModel)
        }

        composable(route = Screens.AddDataScreen.route){

            AddDataScreen(navController = navHostController, sharedViewModel = sharedViewModel)
        }

        composable(route = Screens.ListMedicines.route){

            ListMedicines(navController = navHostController, sharedViewModel = sharedViewModel)
        }


    }

}