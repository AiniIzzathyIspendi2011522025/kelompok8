package com.example.kelompok8.PojoModels.DetailKP

import com.google.gson.annotations.SerializedName

data class DetailKPResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)