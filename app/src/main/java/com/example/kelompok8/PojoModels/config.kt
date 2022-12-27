package com.example.kelompok8.PojoModels

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class config {

    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  okHttpClient
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://ptb-api.husnilkamil.my.id/api/")
            .addConverterFactory( GsonConverterFactory.create())
            .client(getInterceptor())
            .build()
    }

    fun getService() = getRetrofit().create(KPApi::class.java)


}