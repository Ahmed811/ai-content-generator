package com.virastudio.postgeneratorai.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.livedata.observeAsState


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.khalilah.postgeneratorai.R
import com.virastudio.postgeneratorai.AppUtility.copyToClipboard
import com.virastudio.postgeneratorai.AppUtility.shareText
import com.virastudio.postgeneratorai.AppUtility.shareTextToFacebook
import com.virastudio.postgeneratorai.AppViewModel
import com.virastudio.postgeneratorai.MainActivity

import com.virastudio.postgeneratorai.adUnitId
import com.unity3d.ads.UnityAds


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(navController: NavController,appViewModel: AppViewModel){
    var value by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val isGenerateButtonEnabled = value.isNotEmpty()
    val scrollState = rememberScrollState()
    val context= LocalContext.current
    UnityAds.load(adUnitId) // Add Placement ID
    var aiMapData = mapOf<String,String>()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(), modifier = Modifier.padding(10.dp),
                title = {
                    Text("Generate ${appViewModel.appFeatureType.value.toString()} Post")
                }, navigationIcon = {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", modifier = Modifier
                        .size(25.dp)
                        .clickable { navController.popBackStack() },)
                }
            )
        },
    ) {it->
//        fun sendQuestionToAi(userInput:String=""){
//            value=userInput
//            result=value
//
//        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(scrollState),Arrangement.Center,Alignment.CenterHorizontally) {

            Text(text = "${appViewModel.promptType()}: ", textAlign = TextAlign.Start, fontSize = 20.sp)
            TextField(
                value = value,

                onValueChange = {
                                value=it
                },
                label = {                    Text("Example:${appViewModel.hintText()} "
                    )

                     },

                maxLines = 5,
                textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp)
                    .height(100.dp)
                    .border(
                        BorderStroke(
                            2.dp,
                            Color.Cyan
                        ),
                    )
            )
            Spacer(modifier = Modifier.height(3.dp))
            Button(onClick = {


               // sendQuestionToAi(value)

                 val myRequestPost=appViewModel.promptType()+value
                 aiMapData = mapOf( "enable_google_results" to "true","enable_memory" to "false","input_text" to myRequestPost)

                    appViewModel.getResponse(aiMapData)
//                appViewModel.aiMessage.value= apiManager._newsResponse.value.message!!
                value=""

            }, modifier = Modifier

                .padding(5.dp)
                .height(50.dp),
            enabled = isGenerateButtonEnabled


            ) {
                Text(text = stringResource(R.string.generate), fontSize = 18.sp, textAlign = TextAlign.Center)

            }

//           apiManager._newsResponse.let { it->
//               Text(text = it.value.message.toString())
//           }

            AiResponse( context = context,appViewModel )

        }

    }


BackHandler() {
    UnityAds.show(context as? MainActivity, adUnitId) // Context and Add Placement ID
}

}


@SuppressLint("SuspiciousIndentation")
@Composable
fun AiResponse(context: Context,appViewModel: AppViewModel){

  //  val scrollState = rememberScrollState()


    val newsResponse by appViewModel.newsResponse.observeAsState()
    val isLoading by appViewModel.isLoading.observeAsState(false)
    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .wrapContentSize(Alignment.Center)
        )
    } else{
        newsResponse?.let{it->
            Column(
                Modifier
                    .fillMaxWidth()
                ,Arrangement.Center,Alignment.CenterHorizontally) {
                SelectionContainer(modifier = Modifier
                    .padding(10.dp)
                    .border(BorderStroke(2.dp, Color.Green))
                ) {
//                Text(apiManager._newsResponse.value.message!!, modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(20.dp)
//
//                    .verticalScroll(scrollState), textAlign = TextAlign.Center)
                    Text(it.message!!, modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)

                       // .verticalScroll(scrollState)
                        , textAlign = TextAlign.Center)
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp),Arrangement.SpaceAround,Alignment.CenterVertically) {


                Image(painterResource(id = R.drawable.copy) , contentDescription ="Copy to clipboard", modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        copyToClipboard(
                            context = context,
                            appViewModel.newsResponse.value!!.message.toString()
                        )
                    } )


                Image(painterResource(id = R.drawable.share) , contentDescription ="Copy to clipboard", modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        shareText(
                            context,
                            appViewModel.newsResponse.value!!.message.toString()
                        )
                    } )

                Image(painterResource(id = R.drawable.open_facebook) , contentDescription ="Copy to clipboard", modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        shareTextToFacebook(
                            context,
                            appViewModel.newsResponse.value!!.message.toString()
                        )
                    } )

                }

            }
        }
    }




}




@Composable
@Preview(showBackground = true)
fun PostScreenPreview(){
    PostScreen(rememberNavController(), appViewModel = AppViewModel())
}

@Composable
@Preview(showBackground = true)
fun AiResponsePreview(){
    AiResponse( LocalContext.current, AppViewModel())
}