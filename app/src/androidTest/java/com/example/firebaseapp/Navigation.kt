package com.example.firebaseapp


import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavGraph(navController: NavHostController = rememberNavController(), userId: String, isSenior: Boolean) {
  NavHost(navController = navController, startDestination = "taskInput") {
    composable("taskInput") { TaskInputScreen(taskViewModel = viewModel(), userId = userId) }
    composable("taskList") {
      if (isSenior) {
        TaskListScreen(taskViewModel = viewModel(), userId = "")
      } else {
        TaskListScreen(taskViewModel = viewModel(), userId = userId)
      }
    }
  }
}
