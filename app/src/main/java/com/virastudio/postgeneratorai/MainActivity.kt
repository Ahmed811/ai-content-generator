package com.virastudio.postgeneratorai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.virastudio.postgeneratorai.ui.theme.PostGeneratorAiTheme
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.UnityAds

val  unityGameID:String = "5444506";
val  testMode:Boolean = false;
val  adUnitId:String = "inter";
class MainActivity : ComponentActivity() ,IUnityAdsInitializationListener {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            UnityAds.initialize(
                this@MainActivity,
                unityGameID,
                testMode,
                object : IUnityAdsInitializationListener {
                    override fun onInitializationComplete() {
                     //   Toast.makeText(this@MainActivity, "SDK Working", Toast.LENGTH_SHORT).show()
                    }

                    override fun onInitializationFailed(
                        unityAdsInitializationError: UnityAds.UnityAdsInitializationError,
                        s: String
                    ) {
                    //    Toast.makeText(this@MainActivity, "SDK  Not Working", Toast.LENGTH_SHORT).show()
                    }
                })
            PostGeneratorAiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    AppNavigation()
                }
            }
        }
    }
    override fun onInitializationComplete() {

    }

    override fun onInitializationFailed(p0: UnityAds.UnityAdsInitializationError?, p1: String?) {

    }
}

