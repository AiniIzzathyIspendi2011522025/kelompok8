package com.example.kelompok8.PojoModels.TambahLogbook

import com.google.gson.annotations.SerializedName

data class Logbook(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("note")
    val note: Any? = null,

    @field:SerializedName("internship_id")
    val internshipId: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("activities")
    val activities: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("status")
    val status: Int? = null
)