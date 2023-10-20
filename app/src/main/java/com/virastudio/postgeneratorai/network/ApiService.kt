package com.virastudio.postgeneratorai.network

import com.virastudio.postgeneratorai.models.api.ApiRessponse
import com.virastudio.postgeneratorai.models.api.ArtResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("business/content/chatsonic?engine=premium&language=en")
    fun getResponse(@Body question:Map<String,String>):Call<ApiRessponse>
    @POST("business/content/generate-image?engine=premium&language=en&num_copies=1")
    fun getArtResponse(@Body description:Map<String,String>):Call<ArtResponse>
}