
package com.virastudio.postgeneratorai

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.virastudio.postgeneratorai.models.api.ApiRessponse
import com.virastudio.postgeneratorai.models.api.ArtResponse
import com.virastudio.postgeneratorai.network.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.URL

class AppViewModel:ViewModel() {
    var appFeatureType= mutableStateOf(AppFeatureType.FACEBOOK)

    var privacyPolicy:String?=null


    private val _newsResponse = MutableLiveData<ApiRessponse>()
    val newsResponse: LiveData<ApiRessponse>
        get() = _newsResponse
    private val _artResponse = MutableLiveData<ArtResponse>()
    val artResponse: LiveData<ArtResponse>
        get() = _artResponse
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    fun getResponse(question: Map<String, String>) {
        _isLoading.value = true
        val service = Api.retrofitService.getResponse(question)
        service.enqueue(object : Callback<ApiRessponse> {
            override fun onResponse(call: Call<ApiRessponse>, response: Response<ApiRessponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _newsResponse.postValue(response.body())
                    Log.d("ApiManager.res", response.body().toString())
                } else {
                    Log.d("ApiManager.error", response.message())
                }
            }

            override fun onFailure(call: Call<ApiRessponse>, t: Throwable) {
                _isLoading.value = false
                Log.d("ApiManager.fail", t.message.toString())
                t.printStackTrace()
            }
        })
    }
    fun getArtResponse(description: Map<String, String>) {
        _isLoading.value = true
        val service = Api.retrofitService.getArtResponse(description)
        service.enqueue(object : Callback<ArtResponse> {
            override fun onResponse(call: Call<ArtResponse>, response: Response<ArtResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _artResponse.postValue(response.body())
                    Log.d("ApiManager.res", response.body().toString())
                } else {
                    Log.d("ApiManager.error", response.message())
                }
            }

            override fun onFailure(call: Call<ArtResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d("ApiManager.fail", t.message.toString())
                t.printStackTrace()
            }
        })
    }
    suspend fun downloadImage(imageUrl: String, context: Context) {
        val url = URL(imageUrl)
        val connection = withContext(Dispatchers.IO) {
            url.openConnection()
        } as HttpURLConnection
        withContext(Dispatchers.IO) {
            connection.connect()
        }

        val inputStream = connection.inputStream

        // Define the image details
        val displayName = "image.jpg"
        val mimeType = "image/jpeg"

        // Insert the image into MediaStore
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
            put(MediaStore.Images.Media.MIME_TYPE, mimeType)
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/PostGeneratorAi/") // Change this to your desired directory
        }

        val resolver = context.contentResolver
        val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        // Get an OutputStream from the content resolver and write the image
        imageUri?.let {
            resolver.openOutputStream(it)?.use { outputStream ->
                val buffer = ByteArray(1024)
                var bytesRead = inputStream.read(buffer)
                while (bytesRead != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                    bytesRead = inputStream.read(buffer)
                }
            }
        }

        withContext(Dispatchers.IO) {
            inputStream.close()
        }
    }


    fun shareImageUrl(context: Context, imageUrl: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, imageUrl)

        // Optionally, you can set a title for the share dialog
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Image URL")

        // Check if there are apps that can handle the share intent
        if (shareIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(Intent.createChooser(shareIntent, "Share Image URL"))
        } else {
            // Handle the case where no apps can handle the share intent
            Toast.makeText(context, "No apps can handle this action", Toast.LENGTH_SHORT).show()
        }
    }





    fun promptType():String{
        return   when (appFeatureType.value) {
            AppFeatureType.FACEBOOK -> "Write a Facebook post about "
            AppFeatureType.TWITTER -> "Write a tweet about "
            AppFeatureType.LINKEDIN -> "Write a LinkedIn post about "
            AppFeatureType.ARTICLE -> "Write an article about "
            AppFeatureType.CHATGPT -> "Ask Ai about anything! "

            else -> {
                "Generate Image about "
            }
        }
    }







    fun hintText():String{
        return when(appFeatureType.toString()){
            AppFeatureType.CHATGPT.toString() ->"write 10 jokes"
            AppFeatureType.LINKEDIN.toString() ->"Programmer job description"
            else ->"Cooking Fish!"
        }
    }










}