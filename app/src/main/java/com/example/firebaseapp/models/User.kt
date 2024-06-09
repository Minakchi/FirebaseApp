package com.example.firebaseapp.models

data class User(
  val firstName: String,
  val lastName: String,
  val age: Any?,
  val email: String
)

data class Task(
  val id: String,
  val title: String,
  val priority: Int,
  val targetDate: String,
  val isCompleted: Boolean = false
)

