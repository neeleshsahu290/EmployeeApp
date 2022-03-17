package com.example.login.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.example.login.R
import com.example.login.`object`.Employees
import com.example.login.databinding.ActivityEmployeeDetailsBinding
import com.example.login.databinding.ActivityMainBinding
import com.example.login.network.ApiService
import com.example.login.network.Networkcall
import com.example.login.network.RetrofitClient
import kotlinx.coroutines.*
import retrofit2.Response

class EmployeeDetailsActivity : AppCompatActivity() , CoroutineScope by MainScope() {
    private lateinit var binding: ActivityEmployeeDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Employee Details"
        binding = ActivityEmployeeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val bundle = intent.extras
          val message = bundle!!.getString("eid")
        message?.let { getData(it) }


    }



    fun getData (eid: String) {
        launch(Dispatchers.IO) {

            try {
                val task1 = async { Networkcall().getLeaveDetails(eid) }
                val task2 = async { Networkcall().getSaalaryDetails(eid) }
                val result1 = task1.await()
                val result2 = task2.await()
                Log.d(
                    "success",
                     "Leave Details=> " + result1 + "Salary Details=> " + result2
                )
                Handler(Looper.getMainLooper()).post {
                    details()
                    binding.constraint.visibility=View.GONE
                }
            }catch (e: Exception){
                Log.d("catch",e.message.toString())

            }
        }
    }
fun details(){

    val size1= Employees.Leave?.employee?.size
    if (size1!! >0) {

        val first = Employees.Leave?.employee?.get(0)

        val taken_leave = first?.taken_leave
        val total_leave = first?.total_leaves

        binding.leaveTaken.text = taken_leave
        binding.totalLeaves.text = total_leave

    }

    val size= Employees.Salary?.employee?.size
    if (size!! >0) {


        val first = Employees.Salary?.employee?.get(0)
        val ename = first?.ename
        val salary = first?.salary
        val earning_salary = first?.earning_salary
        val present_days = first?.present_days

        binding.ename.text = ename
        binding.salary.text = salary
        binding.earningSalary.text = earning_salary
        binding.presentDays.text = present_days
    }

}

}