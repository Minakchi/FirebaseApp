import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebaseapp.viewmodel.RegisterViewModel
import com.example.firebaseapp.viewmodel.RegistrationResult

@Composable
fun RegisterPage(registerViewModel: RegisterViewModel = viewModel()) {
  val context = LocalContext.current
  val registrationResult = registerViewModel.registrationResult
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    OutlinedTextField(
      value = registerViewModel.username,
      onValueChange = { registerViewModel.username = it },
      label = { Text("Username") },
      modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
      value = registerViewModel.email,
      onValueChange = { registerViewModel.email = it },
      label = { Text("Email") },
      modifier = Modifier.fillMaxWidth(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
      value = registerViewModel.password,
      onValueChange = { registerViewModel.password = it },
      label = { Text("Password") },
      modifier = Modifier.fillMaxWidth(),
      visualTransformation = PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
      value = registerViewModel.confirmPassword,
      onValueChange = { registerViewModel.confirmPassword = it },
      label = { Text("Confirm Password") },
      modifier = Modifier.fillMaxWidth(),
      visualTransformation = PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(
      onClick = { registerViewModel.registerUser() },
      modifier = Modifier.align(Alignment.End)
    ) {
      Text("Register")
    }
    when(registrationResult){
      is RegistrationResult.Failure ->  Toast.makeText(context, registrationResult.message, Toast.LENGTH_SHORT).show()
      RegistrationResult.Loading ->  Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
      RegistrationResult.Success ->  Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
      null -> {}
    }
  }
}

@Preview(showBackground = true)
@Composable
fun RegisterPagePreview() {
  RegisterPage()
}
