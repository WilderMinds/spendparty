package com.samdev.spendparty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samdev.spendparty.extensions.setAppContent
import com.samdev.spendparty.ui.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAppContent {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                MainScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
private fun MainScreen(
    modifier: Modifier = Modifier,
    startRoute: String = Screens.Home.name,
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startRoute,
    ) {
        composable(Screens.Home.name) {
            HomeScreen()
        }
    }
}

enum class Screens {
    Home,
}

