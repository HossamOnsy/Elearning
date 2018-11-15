//package com.hz.elearning
//
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
//import android.util.Log
//import android.view.View
//import android.widget.ArrayAdapter
//import android.widget.Toast
//import com.hz.elearning.models.CourseModelFromDB
//import com.hz.elearning.models.Student
//import com.hz.elearning.models.StudentModelFromDB
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.schedulers.Schedulers
//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.activity_main_teacher.*
//import java.util.*
//
//
//class MainStudentActivity : AppCompatActivity(), DataAdapter.Listener {
//
//
//
//    private val TAG = MainStudentActivity::class.java.simpleName
//
//
//    private var mCompositeDisposable: CompositeDisposable? = null
//
//    private var mAndroidArrayList: ArrayList<Student>? = null
//
//    private var mAdapter: DataAdapter? = null
//
//    private var loginType : String? = "Teacher"
//
//    lateinit var student: StudentModelFromDB
//
//    lateinit var list_of_items: List<CourseModelFromDB.Data?>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        mCompositeDisposable = CompositeDisposable()
//        if(intent.hasExtra("loginType")){
//            loginType = intent.getStringExtra("loginType")
//        }
//
//
//    }
//
//
//    private fun getCourses() {
//        if (student.data.get(0).major.contains("Mecha")) {
//            val restRetrofit = RestRetrofit()
//            val restServiceApi = restRetrofit.buildRetrofit()
//
//            if (restServiceApi != null) {
//                mCompositeDisposable!!.add(
//                        restServiceApi.getMechaCourses()
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribeOn(Schedulers.io())
//                                .subscribe(this::handleResponseGetCourses, this::handleError))
//            }
//        } else {
//            val restRetrofit = RestRetrofit()
//            val restServiceApi = restRetrofit.buildRetrofit()
//
//            if (restServiceApi != null) {
//                mCompositeDisposable!!.add(
//                        restServiceApi.getCourses()
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribeOn(Schedulers.io())
//                                .subscribe(this::handleResponseGetCourses, this::handleError))
//            }
//        }
//    }
//
//    private fun handleResponse(androidList: List<Student>) {
//
//        mAndroidArrayList = ArrayList(androidList)
//        mAdapter = DataAdapter(mAndroidArrayList!!, this)
//
//        rv_android_list.adapter = mAdapter
//    }
//    private fun handleResponseGetCourses(courseModelFromDB: CourseModelFromDB) {
//        if (courseModelFromDB.data != null) {
//            list_of_items = courseModelFromDB.data
//            val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_items)
//            // Set layout to use when the list of choices appear
//            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Set Adapter to Spinner
//            spinner_courses!!.setAdapter(aa)
//            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//
//    private fun handleError(error: Throwable) {
//
//        Log.d(TAG, error.localizedMessage)
//
//        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
//    }
//
//
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mCompositeDisposable?.clear()
//    }
//}