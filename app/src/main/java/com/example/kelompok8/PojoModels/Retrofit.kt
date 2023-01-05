package com.example.kelompok8.PojoModels

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Retrofit {
    class Builder {
        fun baseUrl(baseUrl: String) {

        }

    }

    fun getRetroClientInstance(): Retrofit {
            val gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder()
                .baseUrl("http://ptb-api.husnilkamil.my.id/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
    }
