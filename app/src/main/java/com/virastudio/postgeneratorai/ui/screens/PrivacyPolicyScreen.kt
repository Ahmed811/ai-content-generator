package com.virastudio.postgeneratorai.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.virastudio.postgeneratorai.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(navController: NavController,viewModel: AppViewModel){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(

                title = {
                    Text(text = "Privacy Policy",
                    )
                },
                navigationIcon = {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription ="Back to main menu", modifier = Modifier
                        .clickable {
                            navController.navigateUp()
                        }
                        .size(25.dp) )
                }
            )
        },
    ) {it->
        Text(text = viewModel.privacyPolicy!!,
            Modifier
                .padding(it).padding(20.dp)
                .verticalScroll(
                    rememberScrollState()
                ))
    }

}