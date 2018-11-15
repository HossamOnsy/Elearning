package com.hz.elearning

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.hz.elearning.models.ResponseModel
import com.hz.elearning.models.StudentModelFromDB
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login_semi_official_screen.*

class LoginSemiOfficialScreen : AppCompatActivity() {

    private val TAG = LoginSemiOfficialScreen::class.java.simpleName

    private var mCompositeDisposable: CompositeDisposable? = null

    private var loginType : String? = "Teacher"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_semi_official_screen)

        if(intent.hasExtra("loginType")){
            loginType = intent.getStringExtra("loginType")
        }

        mCompositeDisposable = CompositeDisposable()
        login_btn.setOnClickListener {

            val restRetrofit = RestRetrofit()
            val restServiceApi = restRetrofit.buildRetrofit()

            if (restServiceApi != null) {
                if(loginType.equals("Teacher"))
                mCompositeDisposable!!.add(
                        restServiceApi.getTeacherLogin(username_et.text.toString(), password_et.text.toString())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(this::handleResponseTeacher, this::handleError))
                else{
                    mCompositeDisposable!!.add(
                            restServiceApi.getStudentLogin(username_et.text.toString(), password_et.text.toString())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe(this::handleResponseStudent, this::handleError))
                }
            }
        }
    }
    private fun handleResponseTeacher(s: ResponseModel) {

        if (s.status == 0)
            startActivity(Intent(this, MainTeacherActivity::class.java)
                    .putExtra("loginType",
                            loginType))

    }

    private fun handleResponseStudent(s: StudentModelFromDB) {

        if (s.status == 0)
            startActivity(Intent(this, MainStudentTempActivity::class.java)
                    .putExtra("loginType",
                            loginType).putExtra("StudentModel",s))

    }

    private fun handleError(error: Throwable) {

        Log.d(TAG, error.localizedMessage)

        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }
}
