package com.hz.elearning.models

import com.google.gson.annotations.SerializedName


data class StudentModel(
        @SerializedName("data") val data: List<Data>
) {

    data class Data(
            @SerializedName("gpa") val gpa: String,
            @SerializedName("id") val id: Int,
            @SerializedName("name") val name: String
    )
}