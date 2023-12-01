package com.vellamars.crudfirebasecompose.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vellamars.crudfirebasecompose.nav.Screens
import com.vellamars.crudfirebasecompose.util.SharedViewModel

@Composable
fun MainScreen(navController: NavController){

    Column(
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //get user data button
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(Screens.GetDataScreen.route) }
        ) {
            Text(text = "Get Medicine data")
        }
        
        //Add user data
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(Screens.AddDataScreen.route) }
        ) {
            Text(text = "Add Medicine Data")
        }


        //List data
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(Screens.ListMedicines.route) }
        ) {
            Text(text = "List Data")
        }

    }

}