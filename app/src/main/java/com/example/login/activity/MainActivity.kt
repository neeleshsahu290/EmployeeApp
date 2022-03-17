package com.example.login.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.login.`object`.Employees
import com.example.login.R
import com.example.login.adapter.ViewPagerAdapter

import com.example.login.network.ApiService
import com.example.login.network.RetrofitClient

import com.example.login.databinding.*
import com.example.login.network.Networkcall
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.*
import retrofit2.*


class MainActivity : AppCompatActivity() , CoroutineScope by MainScope() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pager: ViewPager2 // creating object of ViewPager
    private lateinit var tab: TabLayout  // creating object of TabLayout
    private lateinit var constraintLayout: ConstraintLayout
    val animalsArray = arrayOf(
        "Employee",
        "Salary",
        "Leave"
    )
    lateinit var sharedPreferences: SharedPreferences
    lateinit var eid: String

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar?.hide()
        constraintLayout = findViewById(R.id.constlayout)
        // val bundle = intent.extras
        //  val message = bundle!!.getString("eid")
        sharedPreferences =
            getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        eid = sharedPreferences.getString("eid", "") as String
        Log.d("error",eid)
        couritine(eid)

        pager = binding.viewPager
        tab = binding.tabs

        binding.logoutbtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            //set message for alert dialog
            builder.setMessage("Do you really want to logout")
            //performing positive action
            builder.setPositiveButton("Logout") { dialogInterface, which ->
                sharedPreferences.edit().clear().apply()
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            //performing negative action
            builder.setNegativeButton("Cancel") { dialogInterface, which ->
                dialogInterface.dismiss()
            }
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.show()
        }


    }


      fun couritine (eid: String) {
        launch(Dispatchers.IO) {

            try {
                val task1 = async { Networkcall().getemployyeDetails(eid) }
                val task2 = async { Networkcall().getLeaveDetails(eid) }
                val task3 = async { Networkcall().getSaalaryDetails(eid) }
                val result1 = task1.await()
                val result2 = task2.await()
                val result3 = task3.await()
                Log.d(
                    "success",
                    "Employee Details=> " + result1 + "Leave Details=> " + result2 + "Salary Details=> " + result3
                )
                Handler(Looper.getMainLooper()).post {
                    viewpager()
                    constraintLayout.visibility = View.INVISIBLE

                }
            }catch (e: Exception){
                Log.d("catch",e.message.toString())

            }
        }
    }

    fun viewpager(){
        pager.setUserInputEnabled(false)

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        pager.adapter = adapter

        TabLayoutMediator(tab, pager) { tab, position ->
            tab.text = animalsArray[position]
        }.attach()
    }

}