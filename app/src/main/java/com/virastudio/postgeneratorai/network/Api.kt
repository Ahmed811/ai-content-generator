package com.virastudio.postgeneratorai.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Api {

    const val API_KEY="75f188ba-d998-42ab-9df9-8a1c4b93e071"
    const val BASE_URL="https://api.writesonic.com/v2/"
    private val moshi=Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
////////
//    val mediaType = "application/json".toMediaTypeOrNull()
//    val body = RequestBody.create(mediaType, "{\"enable_google_results\":\"true\",\"enable_memory\":false,\"input_text\":\"write\"}")
//        //////////
    val loggin=HttpLoggingInterceptor()
    //to add apiKey to the header
    val httpClient= OkHttpClient.Builder().apply {
        addInterceptor(
            Interceptor {
                    chain->
                val builder=chain.request().newBuilder()

                builder.header("accept", "application/json)")
//                .addHeader("content-type", "application/json")
                builder.header("content-type", "application/json")
                builder.header("X-Api-Key", API_KEY)
                return@Interceptor chain.proceed(builder.build())
            }

        )

        loggin.level=HttpLoggingInterceptor.Level.BODY
        addNetworkInterceptor(loggin)
    }.build()


    private val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi)).client(httpClient)
        .build()


    val retrofitService:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }


}