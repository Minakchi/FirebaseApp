package com.example.firebaseapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskInputScreen(taskViewModel: TaskViewModel, userId: String) {
  var title by remember { mutableStateOf("") }
  var description by remember { mutableStateOf("") }

  Column(modifier = Modifier.padding(16.dp)) {
    OutlinedTextField(
      value = title,
      onValueChange = { title = it },
      label = { Text("Task Title") },
      modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
      value = description,
      onValueChange = { description = it },
      label = { Text("Task Description") },
      modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
      taskViewModel.addTask(title, description, userId)
      title = ""
      description = ""
    }) {
      Text("Add Task")
    }
  }
}
