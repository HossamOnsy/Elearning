package com.hz.elearning

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.hz.elearning.models.CourseModelFromDB
import com.hz.elearning.models.ResponseModel
import com.hz.elearning.models.StudentModelFromDB
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main_teacher.*

class MainTeacherActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }


    private val TAG = LoginSemiOfficialScreen::class.java.simpleName

    private var mCompositeDisposable: CompositeDisposable? = null

    private var loginType: String? = "Teacher"

    lateinit var student: StudentModelFromDB
    lateinit var list_of_items: List<CourseModelFromDB.Data?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_teacher)

        mCompositeDisposable = CompositeDisposable()
        btn_search_for_courses.setOnClickListener(this)
        btn_add_course.setOnClickListener(this)


        spinner_courses!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array

    }


    private fun startAdding() {
        val restRetrofit = RestRetrofit()
        val restServiceApi = restRetrofit.buildRetrofit()

        if (restServiceApi != null) {
            if (!student.data.get(0).courses.contains(list_of_items.get(spinner_courses.selectedItemPosition)!!.code.toString())
                    && (list_of_items.get(spinner_courses.selectedItemPosition)!!.prereq.toString().equals("None") || student.data.get(0).courses.contains(list_of_items.get(spinner_courses.selectedItemPosition)!!.prereq.toString()))) {
                var s = student.data.get((0)).courses
                student.data.get((0)).courses = s + " " + list_of_items.get(spinner_courses.selectedItemPosition)!!.code.toString()
                mCompositeDisposable!!.add(
                        restServiceApi.updateSpecificStudent(student.data.get(0))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(this::handleResponseUpdateSpecificStudent, this::handleError))
            }
        }
    }

    private fun startSearching() {
        val restRetrofit = RestRetrofit()
        val restServiceApi = restRetrofit.buildRetrofit()

        if (restServiceApi != null) {
            mCompositeDisposable!!.add(
                    restServiceApi.getSpecificStudent(et_student_id.text.toString())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(this::handleResponseGetSpecificStudent, this::handleError))
        }
    }

    private fun handleResponseUpdateSpecificStudent(s: ResponseModel) {

        if (s.status == 0)
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleResponseGetSpecificStudent(s: StudentModelFromDB) {
        student = s

        if (!s.data.get(0).id.equals(null)) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            getCourses();
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getCourses() {
        if (student.data.get(0).major.contains("Mecha")) {
            val restRetrofit = RestRetrofit()
            val restServiceApi = restRetrofit.buildRetrofit()

            if (restServiceApi != null) {
                mCompositeDisposable!!.add(
                        restServiceApi.getMechaCourses()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(this::handleResponseGetCourses, this::handleError))
            }
        } else {
            val restRetrofit = RestRetrofit()
            val restServiceApi = restRetrofit.buildRetrofit()

            if (restServiceApi != null) {
                mCompositeDisposable!!.add(
                        restServiceApi.getCourses()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(this::handleResponseGetCourses, this::handleError))
            }
        }
    }

    private fun handleResponseGetCourses(courseModelFromDB: CourseModelFromDB) {
        if (courseModelFromDB.data != null) {
            list_of_items = courseModelFromDB.data
            val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_items)
            // Set layout to use when the list of choices appear
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Set Adapter to Spinner
            spinner_courses!!.setAdapter(aa)
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleError(error: Throwable) {

        Log.d(TAG, error.localizedMessage)

        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_search_for_courses -> {
                startSearching()
            }
            R.id.btn_add_course -> {
                startAdding()
            }
        }

    }
}
