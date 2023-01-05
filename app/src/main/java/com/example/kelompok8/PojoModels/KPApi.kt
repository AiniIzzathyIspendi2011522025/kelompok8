package com.example.kelompok8.PojoModels

import com.example.kelompok8.PojoModels.ListKP.ListKPResponse
import com.example.kelompok8.PojoModels.TambahLogbook.TambahLogbookResponse
import retrofit2.Call
import retrofit2.http.*

interface KPApi {
    @FormUrlEncoded
    @POST("/api/login/")
    fun login(@Field("username") username:String, @Field("password") password:String):Call<LoginResponse>

    @GET("/api/me")
    fun profile(@Header("Authorization") token:String):Call<User>


    @FormUrlEncoded
    @POST("/api/me/update")
    fun updateProfile(
        @Header("Authorization") token:String,
        @Field("name") name:String,
        @Field("email") email:String
    ):Call<UpdateProfilResponse>

    @FormUrlEncoded
    @POST("/api/password")
    fun updatePassword(
        @Header("Authorization")token: String,
        @Field("old_password") old_password:String,
        @Field("new_password") new_password:String,
        @Field("confirm_password") confirm_password:String
    ):Call<ChangePasswordResponse>

    @POST("/api/logout")
    fun logout(@Header("Authorization") token: String
    ):Call<LogoutResponse>

    @GET("/api/my-internship")
    fun listkpresponse(
        @Header("Authorization") token: String, id: Int
    ):Call<ListKPResponse>

    @FormUrlEncoded
    @POST("/api/my-internship/{id}/logbook")
    fun tambahlogbookresponse(
        @Header("Authorization") token: String,
        @Path("id") id : Int,
        @Field("activities") activities:String
    ):Call<TambahLogbookResponse>
}