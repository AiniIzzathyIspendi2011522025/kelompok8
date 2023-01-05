package com.example.kelompok8.PojoModels.ListKP

import com.google.gson.annotations.SerializedName

data class ListKPResponse(

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("internship")
    val internship: List<InternshipItem?>? = null
)