package com.example.firebaseapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
fun HomePage(navController: NavHostController, taskViewModel: TaskViewModel = viewModel()) {
  val tasks = taskViewModel.tasks.collectAsState()

  Scaffold(
    floatingActionButton = {
      FloatingActionButton(onClick = { navController.navigate("addTask") }) {
        Icon(Icons.Default.Add, contentDescription = "Add Task")
      }
    }
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ) {
      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        items(tasks.value) { task ->
          TaskItem(task, onTaskCheckedChange = { taskViewModel.toggleTaskCompletion(task.id, it) }, onDeleteTask = { taskViewModel.deleteTask(task.id) })
        }
      }
    }
  }
}

@Composable
fun TaskItem(task: Task, onTaskCheckedChange: (Boolean) -> Unit, onDeleteTask: () -> Unit) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.fillMaxWidth()
  ) {
    Checkbox(
      checked = task.isCompleted,
      onCheckedChange = { onTaskCheckedChange(it) },
      modifier = Modifier.padding(end = 8.dp)
    )
    Text(task.title, modifier = Modifier.weight(1f))
    IconButton(onClick = onDeleteTask) {
      Icon(Icons.Default.Delete, contentDescription = "Delete Task")
    }
  }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
  val navController = rememberNavController()
  HomePage(navController = navController)
}
