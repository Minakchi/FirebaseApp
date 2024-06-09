package com.example.firebaseapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firebaseapp.viewmodel.TaskViewModel
import com.google.android.gms.tasks.Task

@Composable
fun AddTaskPage(navController: NavHostController, taskViewModel: TaskViewModel = viewModel()) {
  var taskTitle by remember { mutableStateOf("") }
  var priority by remember { mutableStateOf("") }
  var targetDate by remember { mutableStateOf("") }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    OutlinedTextField(
      value = taskTitle,
      onValueChange = { taskTitle = it },
      label = { Text("Task Name") },
      modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
      value = priority,
      onValueChange = { priority = it },
      label = { Text("Priority") },
      modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
      value = targetDate,
      onValueChange = { targetDate = it },
      label = { Text("Target Date") },
      modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(
      onClick = {
        val newTask = Task(
          id = "",  // Firestore will auto-generate the ID
          title = taskTitle,
          priority = priority.toIntOrNull() ?: 0,
          targetDate = targetDate
        )
        taskViewModel.addTask(newTask)
        navController.navigateUp()
      },
      modifier = Modifier.align(Alignment.End)
    ) {
      Text("Add Task")
    }
  }
}

@Preview(showBackground = true)
@Composable
fun AddTaskPagePreview() {
  val navController = rememberNavController()
  AddTaskPage(navController = navController)
}