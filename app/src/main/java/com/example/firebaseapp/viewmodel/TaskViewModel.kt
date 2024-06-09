package com.example.firebaseapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
  private val db = FirebaseFirestore.getInstance()
  private val _tasks = MutableStateFlow<List<Task>>(emptyList())
  val tasks: StateFlow<List<Task>> = _tasks

  init {
    loadTasks()
  }

  private fun loadTasks() {
    db.collection("tasks")
      .addSnapshotListener { snapshot, e ->
        if (e != null || snapshot == null) {
          // Handle error
          return@addSnapshotListener
        }

        val taskList = snapshot.documents.mapNotNull { doc ->
          doc.toObject(Task::class.java)?.copy(id = doc.id)
        }
        _tasks.value = taskList
      }
  }

  fun addTask(task: Task) {
    db.collection("tasks")
      .add(task)
  }

  fun deleteTask(taskId: String) {
    db.collection("tasks")
      .document(taskId)
      .delete()
  }

  fun toggleTaskCompletion(taskId: String, isCompleted: Boolean) {
    db.collection("tasks")
      .document(taskId)
      .update("isCompleted", isCompleted)
  }
}
