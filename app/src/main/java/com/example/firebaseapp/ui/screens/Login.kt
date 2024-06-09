package com.example.firebaseapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginPage() {
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  val context = LocalContext.current
  val auth = FirebaseAuth.getInstance()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    OutlinedTextField(
      value = email,
      onValueChange = { email = it },
      label = { Text("Email") },
      modifier = Modifier.fillMaxWidth(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
      value = password,
      onValueChange = { password = it },
      label = { Text("Password") },
      modifier = Modifier.fillMaxWidth(),
      visualTransformation = PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(
      onClick = {
        auth.signInWithEmailAndPassword(email, password)
          .addOnCompleteListener { task ->
            if (task.isSuccessful) {
              Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
            } else {
              Toast.makeText(context, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
          }
      },
      modifier = Modifier.align(Alignment.End)
    ) {
      Text("Login")
    }
  }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
  LoginPage()
}

