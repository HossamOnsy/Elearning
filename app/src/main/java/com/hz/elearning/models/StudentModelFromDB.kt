package com.hz.elearning.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class StudentModelFromDB(
        @SerializedName("data") val data: List<Data>,
        @SerializedName("status") var status: Int = 0

) : Parcelable {
    public data class Data(
            @SerializedName("courses") var courses: String, // COM155 MAT151 COM155 MAT351 MFG455 MAT261 MSE355 ENG256 MSE454 MAT151 BSC152 GSE153 GSE154 COM155 ENG156 MAT161 BSC162 GSE163
            @SerializedName("gpa") val gpa: Double, // 3.7
            @SerializedName("hours") val hours: Int, // 2
            @SerializedName("id") val id: String, // 143131
            @SerializedName("major") val major: String, // Mechatronics Systems Engineering
            @SerializedName("name") val name: String, // Sam
            @SerializedName("password") val password: String // abc123
    ) : Parcelable {
        constructor(source: Parcel) : this(
                source.readString(),
                source.readDouble(),
                source.readInt(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
            writeString(courses)
            writeDouble(gpa)
            writeInt(hours)
            writeString(id)
            writeString(major)
            writeString(name)
            writeString(password)
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<Data> = object : Parcelable.Creator<Data> {
                override fun createFromParcel(source: Parcel): Data = Data(source)
                override fun newArray(size: Int): Array<Data?> = arrayOfNulls(size)
            }
        }
    }

    constructor(source: Parcel) : this(
            ArrayList<Data>().apply { source.readList(this, Data::class.java.classLoader) },
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeList(data)
        writeInt(status)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<StudentModelFromDB> = object : Parcelable.Creator<StudentModelFromDB> {
            override fun createFromParcel(source: Parcel): StudentModelFromDB = StudentModelFromDB(source)
            override fun newArray(size: Int): Array<StudentModelFromDB?> = arrayOfNulls(size)
        }
    }
}