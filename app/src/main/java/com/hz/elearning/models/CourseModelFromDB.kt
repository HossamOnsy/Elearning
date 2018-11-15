package com.hz.elearning.models

import com.google.gson.annotations.SerializedName


data class CourseModelFromDB(
        @SerializedName("data") val data: List<Data?>?
) {

    data class Data(
            @SerializedName("Code") val code: String?, // ISE564
            @SerializedName("HourPermitted") val hourPermitted: String?, // 3
            @SerializedName("Prereq") val prereq: String?, // ISE554
            @SerializedName("Semster") val semster: String?, // 2
            @SerializedName("Subject") val subject: String?, // Graduation Project (Part II)
            @SerializedName("Year") val year: String? // 5
    ) {
        override fun toString(): String {
            return "$subject+$code"
        }
    }
}