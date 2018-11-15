package com.hz.elearning.models

import com.google.gson.annotations.SerializedName


data class ResponseModel(
        @SerializedName("status") val status: Int?, // 0
        @SerializedName("message") val message: String? // Success
)