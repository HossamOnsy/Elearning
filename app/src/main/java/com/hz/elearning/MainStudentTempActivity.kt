package com.hz.elearning

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.hz.elearning.models.Student
import com.hz.elearning.models.StudentModelFromDB
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.content.Intent
import android.net.Uri


class MainStudentTempActivity : AppCompatActivity() {





    private val TAG = MainStudentTempActivity::class.java.simpleName

    lateinit var student : StudentModelFromDB;
    private var mCompositeDisposable: CompositeDisposable? = null

    private var mAndroidArrayList: ArrayList<String>? = null

//    private var mAdapter: DataAdapter? = null

    private var loginType : String? = "Teacher"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadJSON()
        btn_go_to_pdf.setOnClickListener{
            btn_go_to_pdf.isClickable=false
            if(student.data.get(0).major.contains("Mecha")){
                val url = "https://drive.google.com/file/d/11SYRa_wkI9PwyXrLN3kEu9daXm6t7_CM/view"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                btn_go_to_pdf.isClickable=true
            }
            else
            {
                val url = "https://drive.google.com/file/d/1gf3UMqQZQbIdjr_PlPMAGm-PFsschv_V/view"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                btn_go_to_pdf.isClickable=true
            }
//
        }
    }



    private fun loadJSON() {
//        presenter.loadData
        if(intent.hasExtra("StudentModel")) {
             student = intent.getParcelableExtra<StudentModelFromDB>("StudentModel")
            handleResponse(student.data.get(0).courses)
        }

    }

    private fun handleResponse(coursesLList : String) {

        val stringArray = coursesLList.split(" ")
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, stringArray)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_courses!!.setAdapter(aa)

    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }
}