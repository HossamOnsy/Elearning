package com.hz.elearning.models

import com.google.gson.annotations.SerializedName


data class Student(
        @SerializedName("gpa") val gpa: String,
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String
)