package com.example.kelompok8.PojoModels

import com.google.gson.annotations.SerializedName

data class ChangePasswordResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)