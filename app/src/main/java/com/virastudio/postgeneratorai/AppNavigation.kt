package com.virastudio.postgeneratorai

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.virastudio.postgeneratorai.ui.screens.ArtScreen
import com.virastudio.postgeneratorai.ui.screens.MainScreen
import com.virastudio.postgeneratorai.ui.screens.PostGeneratorScreen
import com.virastudio.postgeneratorai.ui.screens.PostScreen
import com.virastudio.postgeneratorai.ui.screens.PrivacyPolicyScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val appViewModel= viewModel<AppViewModel>()


    NavHost(navController = navController, startDestination = AppRoutes.mainScreen ){
        composable(AppRoutes.mainScreen){
            MainScreen(navController,appViewModel)
        }
        composable(AppRoutes.postGeneratorScreen){
            PostGeneratorScreen(navController,appViewModel)
        }
        composable(AppRoutes.postScreen){
            PostScreen(navController,appViewModel)
        }
        composable(AppRoutes.artScreen){
            ArtScreen(navController,appViewModel)
        }
        composable(AppRoutes.privacyScreen){
            PrivacyPolicyScreen(navController,appViewModel)
        }

    }
}