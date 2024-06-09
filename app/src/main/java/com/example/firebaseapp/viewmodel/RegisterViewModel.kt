package com.example.firebaseapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
  private val auth: FirebaseAuth = Firebase.auth



  var username by mutableStateOf("")
  var email by mutableStateOf("")
  var password by mutableStateOf("")
  var confirmPassword by mutableStateOf("")
  var registrationResult by mutableStateOf<RegistrationResult?>(null)

  fun registerUser() {
    if (password == confirmPassword) {
      viewModelScope.launch {
        registrationResult = RegistrationResult.Loading
        auth.createUserWithEmailAndPassword(email, password)
          .addOnCompleteListener { task ->
            registrationResult = if (task.isSuccessful) {
              RegistrationResult.Success
            } else {
              RegistrationResult.Failure(task.exception?.message ?: "Registration failed")
            }
          }
      }
    } else {
      registrationResult = RegistrationResult.Failure("Passwords do not match")
    }
  }
}

sealed class RegistrationResult {
  data object Loading : RegistrationResult()
  data object Success : RegistrationResult()
  data class Failure(val message: String) : RegistrationResult()
}





