package com.example.firebaseapp

import RegisterPage
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebaseapp.models.User
import com.example.firebaseapp.ui.theme.FirebaseAppTheme
import com.example.firebaseapp.viewmodel.RegisterViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.util.HashMap

class FirebaseActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val db = Firebase.firestore

    enableEdgeToEdge()
    setContent {
      FirebaseAppTheme {
//        val state = remember {
//          mutableStateOf(listOf(User))
//          LaunchedEffect(key1 = Unit) {
//            getUser(db).collect {
//              val user = User(
////                id = it["id"].toString(),
//                firstName = it["firstName"].toString(),
//                lastName = it["lastName"].toString(),
//                age = it["age"],
//                email =it["email"].toString()
//
//              )
//              state += user
//            }
//          }
//
//          if (state!=null){
//            val user = state.value
//            println("First Name: $firstName")
//            println("Last Name: $lastName")
//          }
//          }
//        }
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Column(Modifier.padding(innerPadding)) {

//            val user= hashMapOf(
//              "firstName" to "Ada",
//              "lastName" to "Lovelace",
//              "age" to 18,
//              "email" to "ada@gmail.com"
//            )
//
//            LaunchedEffect(key1 = Unit) {
//              addToDb(db = db, data = user,  document = "users")
//            }
            val viewModel: RegisterViewModel = viewModel()
            RegisterPage(viewModel)
          }
        }
      }
    }
  }


  fun addToDb(db: FirebaseFirestore, data: HashMap<String, *>, document: String) {
    db.collection(document)
      .add(data)
      .addOnSuccessListener {
        Log.d("TAG", "DocumentSnapshot added with ID: ${it.id}")
      }
      .addOnFailureListener {
        Log.d("TAG", "DocumentSnapshot added with ID: ${it.message}")
      }
  }

  fun getUser(db: FirebaseFirestore) = callbackFlow {
    db.collection("users").get()
      .addOnSuccessListener { result ->
        for (document in result) {
          Log.d("TAG", "DocumentSnapshot added with ID: ${document.id}")
          Log.d("TAG", "${document.data}")
          trySend(document.data)
        }
      }
      .addOnFailureListener {
        Log.d("TAG", "DocumentSnapshot added with ID: ${it.message}")
        close(it)
      }
    awaitClose()
  }
}
