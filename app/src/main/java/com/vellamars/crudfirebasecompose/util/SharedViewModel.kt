package com.vellamars.crudfirebasecompose.util

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class SharedViewModel : ViewModel() {


    private val _isLoading = MutableStateFlow<Boolean?>(false)
    val isLoading: StateFlow<Boolean?> = _isLoading.asStateFlow()


    private val _medicines = mutableStateOf<List<MedicineData>>(emptyList())
    val medicines: State<List<MedicineData>> = _medicines


    var state by mutableStateOf(MedicineData())
        private set

    fun fetchDataMedicine(){
        _isLoading.value = true

    }


    init {
        fetchDataFromFirestore()
    }


    fun fetchDataFromFirestore() = CoroutineScope(Dispatchers.IO).launch{
        val fireStoreRef = Firebase.firestore.collection("medicine")

        try {
            fireStoreRef.get().addOnSuccessListener {
                val medicineData = it.toObjects<MedicineData>()
                _medicines.value = medicineData
            }
        }catch (e: Exception){
            //Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }


    fun deleteMedicine(medicine: MedicineData) {
        // Eliminamos el medicamento de la base de datos
        val medicineRef = Firebase.firestore.collection("medicine").document(medicine.medicineID)
        medicineRef.delete()
    }



    fun saveData(medicineData: MedicineData, context: Context) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("medicine")
            .document(medicineData.medicineID)

        try {
            fireStoreRef.set(medicineData)
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

//    fun saveData(userData: UserData, context: Context) = CoroutineScope(Dispatchers.IO).launch{
//
//        val fireStoreRef = Firebase.firestore.collection("user").document(userData.userID)
//
//        try {
//            fireStoreRef.set(userData).addOnSuccessListener {
//                Toast.makeText(context, "Successfully data saved!", Toast.LENGTH_LONG).show()
//            }
//        }catch (e: Exception){
//            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
//        }
//    }




    fun retrieveData(medicineID: String, context: Context, data: (MedicineData) -> Unit) = CoroutineScope(Dispatchers.IO).launch{
        val fireStoreRef = Firebase.firestore.collection("medicine").document(medicineID)

        try {
            fireStoreRef.get().addOnSuccessListener {
                if(it.exists()){
                    val medicineData = it.toObject<MedicineData>()!!
                    data(medicineData)
                }else{
                    Toast.makeText(context, "No user data found", Toast.LENGTH_LONG).show()
                }
            }
        }catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }



    fun deleteData(medicineID: String, context: Context, navController: NavController) = CoroutineScope(Dispatchers.IO).launch{
        val fireStoreRef = Firebase.firestore.collection("medicine").document(medicineID)

        try {
            fireStoreRef.delete().addOnSuccessListener {
                Toast.makeText(context, "Successfully data saved!", Toast.LENGTH_LONG).show()
            }
        }catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }


}