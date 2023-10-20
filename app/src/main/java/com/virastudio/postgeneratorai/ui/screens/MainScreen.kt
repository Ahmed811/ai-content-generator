package com.virastudio.postgeneratorai.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.khalilah.postgeneratorai.R
import com.virastudio.postgeneratorai.AppRoutes
import com.virastudio.postgeneratorai.AppViewModel


@Composable
fun MainScreen(navController: NavController,viewModel:AppViewModel){


    val context= LocalContext.current
//    UnityAds.load(adUnitId) // Add Placement ID



    Column(
        Modifier
            .fillMaxWidth()
            .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement =Arrangement.Center) {

        Image(painterResource(id = R.drawable.post), contentDescription ="App Logo" , modifier = Modifier.size(250.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text =context.getString(R.string.app_name), style = MaterialTheme.typography.displayLarge.copy(fontSize = 30.sp))
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            navController.navigate(AppRoutes.postGeneratorScreen){
                popUpTo(AppRoutes.mainScreen){
                    inclusive = true
                }

            }
        }) {
            Text(text = stringResource(R.string.generate_post) , fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {

            val privacyPolicyInputStream =context. resources.openRawResource(R.raw.privacy_policy)
            val privacyPolicyBytes = privacyPolicyInputStream.readBytes()
            val privacyPolicyText = String(privacyPolicyBytes)
            viewModel.privacyPolicy=privacyPolicyText
            navController.navigate("Privacy")

         //   UnityAds.show(context as? MainActivity, adUnitId) // Context and Add Placement ID

        }) {
            Text(text = stringResource(R.string.privacy_policy) , fontSize = 20.sp)
        }
    }
}




@Composable
@Preview(showSystemUi = true)
fun MainScreenPreview(){
    MainScreen(rememberNavController(), AppViewModel())
}