package com.hz.elearning.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hz.elearning.LoginSemiOfficialScreen
import com.hz.elearning.R
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

//    @BindView (R.id.teacher_login_btn)
//    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        teacher_login_btn.setOnClickListener {
            startActivity(Intent(this, LoginSemiOfficialScreen::class.java)
                    .putExtra("loginType","Teacher"))
        }

        student_login_btn.setOnClickListener {
            startActivity(Intent(this, LoginSemiOfficialScreen::class.java)
                    .putExtra("loginType","Student"))
        }
    }
}
