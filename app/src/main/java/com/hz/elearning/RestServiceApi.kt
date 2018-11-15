package com.hz.elearning

import com.hz.elearning.models.CourseModelFromDB
import com.hz.elearning.models.ResponseModel
import com.hz.elearning.models.Student
import com.hz.elearning.models.StudentModelFromDB
import io.reactivex.Observable
import retrofit2.http.*


interface RestServiceApi {
    @FormUrlEncoded
    @POST("TeacherLogin")
    fun getTeacherLogin(
            @Field("username") uname: String,
            @Field("password") password: String
    ): Observable<ResponseModel>

    @FormUrlEncoded
    @POST("getStudent")
    fun getSpecificStudent(@Field("id") uname: String): Observable<StudentModelFromDB>

    @FormUrlEncoded
    @POST("StudentLogin")
    fun getStudentLogin(
            @Field("username") uname: String,
            @Field("password") password: String
    ): Observable<StudentModelFromDB>

    @POST("updateStudent")
    fun updateSpecificStudent(@Body student: StudentModelFromDB.Data): Observable<ResponseModel>


    @GET("Courses")
    fun getCourses(): Observable<CourseModelFromDB>

    @GET("MechaCourses")
    fun getMechaCourses(): Observable<CourseModelFromDB>


    @GET("g")
    fun getData(): Observable<List<Student>>


}