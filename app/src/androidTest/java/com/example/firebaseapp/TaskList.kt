package com.example.firebaseapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskListScreen(taskViewModel: TaskViewModel, userId: String) {
  val tasks by taskViewModel.tasks.collectAsState()

  LaunchedEffect(userId) {
    taskViewModel.getTasks(userId)
  }

  LazyColumn(
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(tasks) { task ->
      TaskItem(task)
    }
  }
}

@Composable
fun TaskItem(task: Task) {
  Card(modifier = Modifier.fillMaxWidth()) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(text = task.title, style = MaterialTheme.typography.titleMedium)
      Text(text = task.description, style = MaterialTheme.typography.bodyMedium)
    }
  }
}
