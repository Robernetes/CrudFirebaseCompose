package com.vellamars.crudfirebasecompose.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                .padding(start = 30.dp, end = 30.dp, bottom = 30.dp)
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
    val medicines = viewModel.medicines.value.sortedBy { it.dateMedicine }
//    val medicines = viewModel.medicines.value.sortedByDescending { it.dateMedicine }


    LazyColumn {

        items(medicines) { medicine ->
            MedicineItem(medicine, viewModel)
        }
    }
}


@Composable
fun MedicineItem(medicine: MedicineData, viewModel: SharedViewModel) {
    val medicines = viewModel.medicines.value
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(), Arrangement.SpaceBetween) {
//            Text(text = medicine.name)
            TextMod(text = medicine.name)
            Divider()
            Text(text = medicine.indication)
//            Text(text = medicine.dateMedicine)
            Spacer(modifier = Modifier.weight(2.0f))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                IconButton(onClick = {
                    // Eliminamos el medicamento de la base de datos
                    viewModel.deleteMedicine(medicine)
                    viewModel.fetchDataFromFirestore()
                    // Eliminamos el medicamento de la lista
                    //medicines.remove(medicine)
                }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
                }

//                Text(text = medicine.dateMedicine)
                TextDate(text = medicine.dateMedicine)
            }

//            IconButton(onClick = {
//                // Eliminamos el medicamento de la base de datos
//                viewModel.deleteMedicine(medicine)
//                viewModel.fetchDataFromFirestore()
//                // Eliminamos el medicamento de la lista
//                //medicines.remove(medicine)
//            }) {
//                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
//            }
        }
    }
}

@Composable
fun TextMod(text: String){
    Text(text = text,
        modifier = Modifier.padding(5.dp),
        textAlign = TextAlign.Start,
        fontStyle = FontStyle.Italic,
        fontSize = 15.sp,
        color = Color.Blue,

        )
}



@Composable
fun TextDate(text: String){
    Text(text = text,
        modifier = Modifier.padding(2.dp),
        textAlign = TextAlign.End,
        fontSize = 11.sp,
        color = Color.Black,

        )
}
