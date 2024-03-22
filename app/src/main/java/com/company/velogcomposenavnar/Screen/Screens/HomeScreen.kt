package com.company.velogcomposenavnar.Screen.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.company.velogcomposenavnar.Screen.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Column(

    ) {
        Text(
            text = "HomeScreen",
            fontSize = 50.sp
        )
        Spacer(Modifier.size(8.dp))
        Button(onClick = {
            navController.navigate(Screen.botongScreen1.route)
        }) {
            Text("To bottomScreen1")
        }
        Spacer(Modifier.size(8.dp))
        Button(onClick = {
            navController.navigate(Screen.botongScreen2.route)
        }) {
            Text("To bottomScreen2")
        }
    }
}