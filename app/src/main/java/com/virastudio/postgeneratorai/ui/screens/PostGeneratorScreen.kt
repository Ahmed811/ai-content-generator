package com.virastudio.postgeneratorai.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.khalilah.postgeneratorai.R
import com.virastudio.postgeneratorai.AppFeatureType
import com.virastudio.postgeneratorai.AppInfo
import com.virastudio.postgeneratorai.AppRoutes
import com.virastudio.postgeneratorai.AppViewModel

import com.virastudio.postgeneratorai.models.postCardList
import com.virastudio.postgeneratorai.ui.components.PostCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostGeneratorScreen(navController: NavController,appViewModel: AppViewModel){
    val context= LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
                title = {
                    Row() {
                        Text(context.getString(R.string.app_name))
                    }

                },
                navigationIcon = {
                    Row() {
                        Spacer(modifier = Modifier.width(20.dp))
                        Image(painter = painterResource(id = R.drawable.email), contentDescription ="Send Mail to developer" ,
                        Modifier
                            .size(35.dp).clickable { AppInfo.sendEmail(context,AppInfo.developerMail,"From post ai app","From post ai app") }
                        )
                    }

                },
                actions = {
                    Row() {
                        Image(painter = painterResource(id = R.drawable.star), contentDescription ="Rate the app" ,
                            Modifier
                                .size(35.dp).clickable { AppInfo.openPlayStoreForReview(context.packageName,context)}
                                )
                        Spacer(modifier = Modifier.width(20.dp))
                    }



                }
            )
        },
    ) {it->
        Column(
            Modifier
                .fillMaxHeight()
                .padding(it), Arrangement.SpaceAround, Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(50.dp))
            LazyVerticalGrid( columns = GridCells.Fixed(2), modifier = Modifier.fillMaxHeight()){
                items(postCardList){ data->
                    PostCard(postCardModel = data){
                        appViewModel.appFeatureType.value=data.appFeatureType
                        if (appViewModel.appFeatureType.value==AppFeatureType.ART){
                            navController.navigate(AppRoutes.artScreen)
                        }else{
                            navController.navigate(AppRoutes.postScreen)
                        }

                    }

                }
            }

        }

    }




}

