package com.example.kelompok8.PojoModels

import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private const.val BASE_URL: String )
        get() = "http://ptb-api.husnilkamil.my.id/api/"

    val instance = KPApi by lazy {
                 val retrofit = Retrofit.Builder()
                     .baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory).create())
                    .build()

                retrofit.create(KPApi::class.java)

            }
}

private fun Any.addConverterFactory(gsonConverterFactory: Any): Any {

}
