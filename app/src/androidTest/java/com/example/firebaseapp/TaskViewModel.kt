package com.example.firebaseapp


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class Task(val id: String, val title: String, val description: String, val userId: String)

class TaskViewModel : ViewModel() {
  private val db = FirebaseFirestore.getInstance()
  private val _tasks = MutableStateFlow<List<Task>>(emptyList())
  val tasks: StateFlow<List<Task>> = _tasks

  fun addTask(title: String, description: String, userId: String) {
    val newTask = Task(id = db.collection("tasks").document().id, title = title, description = description, userId = userId)
    db.collection("tasks").document(newTask.id).set(newTask)
  }

  fun getTasks(userId: String) {
    db.collection("tasks").whereEqualTo("userId", userId).addSnapshotListener { snapshot, e ->
      if (e != null) {
        return@addSnapshotListener
      }
      if (snapshot != null) {
        val tasksList = snapshot.documents.map { doc ->
          doc.toObject(Task::class.java)!!
        }
        _tasks.update { tasksList }
      }
    }
  }

  fun getAllTasks() {
    db.collection("tasks").addSnapshotListener { snapshot, e ->
      if (e != null) {
        return@addSnapshotListener
      }
      if (snapshot != null) {
        val tasksList = snapshot.documents.map { doc ->
          doc.toObject(Task::class.java)!!
        }
        _tasks.update { tasksList }
      }
    }
  }
}
