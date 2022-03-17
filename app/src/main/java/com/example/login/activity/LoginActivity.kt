package com.example.login.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.login.R
import com.example.login.dataclass.loginModelClass
import com.example.login.network.ApiService
import com.example.login.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {
    lateinit var userame :String
    lateinit var password :String
    lateinit var layout: ConstraintLayout
    lateinit var linearLayout:LinearLayout
    lateinit var animation:Animation
    lateinit var warningmsg:TextView
    lateinit var sharedPreferences: SharedPreferences


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        val isloggedin =sharedPreferences.getBoolean("isloggedin",false)
        setContentView(R.layout.activity_login)
        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
        val Button : Button = findViewById(R.id.lgn_btn)
        val susername :EditText=findViewById(R.id.username1)
        val spassword:EditText=findViewById(R.id.password1)
        warningmsg=findViewById(R.id.warningmsg)
        layout=findViewById(R.id.layoutloading)

        if (isloggedin){
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        Button.setOnClickListener {
            userame =susername.text.toString()
            password=spassword.text.toString()
            animation=AnimationUtils.loadAnimation(applicationContext, R.anim.slide)
            linearLayout =findViewById(R.id.lted)
            if (userame.isEmpty()) {
                linearLayout.setBackgroundResource(R.color.yellow)
                warningmsg.text ="username is empty"
                linearLayout.setVisibility(View.VISIBLE)
                linearLayout.startAnimation(animation)
                Toast.makeText(this, "username is empty", Toast.LENGTH_SHORT).show()
            }else if (password.isEmpty()) {
                linearLayout.setBackgroundResource(R.color.yellow)
                warningmsg.text ="password is empty"
                linearLayout.setVisibility(View.VISIBLE)
                linearLayout.startAnimation(animation)
                    Toast.makeText(this, "password is empty", Toast.LENGTH_SHORT).show()
                }
            else {
                sendData(usename = userame.trim(), password = password.trim())


            }
        }
    }

    fun sendData(usename:String, password:String) {
        val retrofitBuilder = RetrofitClient().getClient()?.create(ApiService::class.java)
        val login = loginModelClass(username= usename,password = password)
        val retrofitData = retrofitBuilder?.login(login)
        layout.setVisibility(View.VISIBLE)

        retrofitData?.enqueue(object : Callback<loginModelClass?> {
        override fun onResponse(call: Call<loginModelClass?>, response: Response<loginModelClass?>) {
                val responsemsg= response.body()?.msg
            val eid = response.body()?.eid
            if (responsemsg == "Login Successfull") {
                if (eid != null) {
                    savepreferences(eid)
                }
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("eid", eid)
                startActivity(intent)
                finish()
            }else{
                layout.setVisibility(View.INVISIBLE)
                linearLayout.setBackgroundResource(R.color.red)
                warningmsg.text ="$responsemsg"
                linearLayout.setVisibility(View.VISIBLE)
                linearLayout.startAnimation(animation)
            }
            }
            override fun onFailure(call: Call<loginModelClass?>, t: Throwable) {
                layout.setVisibility(View.INVISIBLE)
                linearLayout.setBackgroundResource(R.color.red)
                warningmsg.text ="Failure due to server issue"
                linearLayout.setVisibility(View.VISIBLE)
                linearLayout.startAnimation(animation)

            }
        })
    }
    fun savepreferences (eid:String){
        sharedPreferences.edit().putBoolean("isloggedin",true).apply()
        sharedPreferences.edit().putString("eid",eid).apply()
    }



}