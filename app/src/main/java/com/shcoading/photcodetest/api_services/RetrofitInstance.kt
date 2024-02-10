package com.shcoading.photcodetest.api_services


import androidx.viewbinding.BuildConfig

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitInstance {
    companion object {

        val BASE_URL = "https://data.cityofnewyork.us/"

        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                logging.setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            val client =
                OkHttpClient.Builder().addInterceptor(logging).connectTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(3, TimeUnit.MINUTES).writeTimeout(3, TimeUnit.MINUTES).build()

            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(client).build()
        }






        val api by lazy {
            retrofit.create(RestApiMethods::class.java)
        }



    }
}