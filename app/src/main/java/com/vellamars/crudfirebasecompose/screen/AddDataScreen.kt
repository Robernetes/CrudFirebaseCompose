package com.vellamars.crudfirebasecompose.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vellamars.crudfirebasecompose.util.SharedViewModel
import com.vellamars.crudfirebasecompose.util.MedicineData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDataScreen(navController: NavController, sharedViewModel: SharedViewModel){


    var medicineID: String by remember { mutableStateOf("") }
    var name: String by remember { mutableStateOf("") }
    var indication: String by remember { mutableStateOf("") }
    var dateMedicine: String by remember { mutableStateOf("") }
    var ageInt: Int by remember { mutableStateOf(0) }

    val context = LocalContext.current


    //main layout
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

            //medicineID
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),value = medicineID, onValueChange = {medicineID = it}, label = { Text(text = "medicineID") })

            //name
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),value = name, onValueChange = {name = it}, label = { Text(text = "Name") })

            //Indications
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),value = indication, onValueChange = {indication = it}, label = { Text(text = "Indication") })

            //date
//            OutlinedTextField(modifier = Modifier.fillMaxWidth(),value = dateMedicine,
//                onValueChange = {
//                    dateMedicine = it
//                    //if(dateMedicine.isNotEmpty()){ageInt = dateMedicine.toInt()}
//                },
//                label = { Text(text = "Date") },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

            //Button save
            Button(
                modifier = Modifier.padding(top = 50.dp).fillMaxWidth(),
                onClick = {
                    val medicineData = MedicineData(medicineID = medicineID, name = name, indication = indication, dateMedicine = getDateMedicine() )
                    sharedViewModel.saveData(medicineData = medicineData, context = context)
                    medicineID = ""
                    indication = ""
                    name = ""
                }
            ) {

                Text(text = "Save")
            }

        }

    }


}


@SuppressLint("SimpleDateFormat")
private fun getDateMedicine(): String {
    val date = Date()
    val fmt = SimpleDateFormat("dd/M/yyyy hh:mm:ss aaa")
    fmt.timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
    return fmt.format(date)
}