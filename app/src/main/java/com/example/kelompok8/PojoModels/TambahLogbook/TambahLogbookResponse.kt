package com.example.kelompok8.PojoModels.TambahLogbook

import com.google.gson.annotations.SerializedName

data class TambahLogbookResponse(

    @field:SerializedName("logbook")
    val logbook: Logbook? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)