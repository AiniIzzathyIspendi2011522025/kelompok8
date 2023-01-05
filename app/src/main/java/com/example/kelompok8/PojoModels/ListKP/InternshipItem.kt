package com.example.kelompok8.PojoModels.ListKP

import com.google.gson.annotations.SerializedName

data class InternshipItem(

    @field:SerializedName("end_at")
    val endAt: String? = null,

    @field:SerializedName("agency")
    val agency: String? = null,

    @field:SerializedName("supervisor_id")
    val supervisorId: Int? = null,

    @field:SerializedName("grade")
    val grade: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("start_at")
    val startAt: Any? = null,

    @field:SerializedName("supervisor")
    val supervisor: String? = null,

    @field:SerializedName("status")
    val status: Int? = null
)