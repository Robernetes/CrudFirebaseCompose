package com.vellamars.crudfirebasecompose.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vellamars.crudfirebasecompose.util.MedicineData
import com.vellamars.crudfirebasecompose.util.SharedViewModel

@Composable
fun ListMedicines(navController: NavController, sharedViewModel: SharedViewModel){
    val medicines = sharedViewModel.medicines.value
    sharedViewModel.fetchDataFromFirestore()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        //Back button
        Row(
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back_button")
            }
        }




        //Add data layout
        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            MedicineList(viewModel = sharedViewModel)

           //Text(text = "List all medicines")
        }
        

    }
}



@Composable
fun MedicineList(viewModel: SharedViewModel) {
    val medicines = viewModel.medicines.value

    LazyColumn {

        items(medicines) { medicine ->
            MedicineItem(medicine, viewModel)
        }
    }
}


@Composable
fun MedicineItem(medicine: MedicineData, viewModel: SharedViewModel) {
    val medicines = viewModel.medicines.value
    Card(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = medicine.name)
            Divider()
            Text(text = medicine.indication)
            Text(text = medicine.dateMedicine)

            IconButton(onClick = {
                // Eliminamos el medicamento de la base de datos
                viewModel.deleteMedicine(medicine)
                viewModel.fetchDataFromFirestore()
                // Eliminamos el medicamento de la lista
                //medicines.remove(medicine)
            }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            }
        }
    }
}