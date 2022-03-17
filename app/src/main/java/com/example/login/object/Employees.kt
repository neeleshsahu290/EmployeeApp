package com.example.login.`object`

import com.example.login.dataclass.Leavelist
import com.example.login.dataclass.employeelist
import com.example.login.dataclass.salarylist
import com.google.gson.Gson
import com.google.gson.GsonBuilder

object Employees {
    val builder = GsonBuilder()
    val gson: Gson = builder.create()
    var employee: employeelist? = null
    var Salary: salarylist?=null
    var Leave: Leavelist?=null
    var SalaryDetails:salarylist?=null
    var LeaveDetails:Leavelist?=null

    fun addEmployee(employe:String) {
     employee = gson.fromJson(employe, employeelist::class.java)
    }
    fun addsalary (salary:String) {
        Salary = gson.fromJson(salary, salarylist::class.java)
    }
    fun addleave(leave:String){
        Leave = gson.fromJson(leave,Leavelist::class.java)
    }
    fun addsalarydetails (salary:String) {
        SalaryDetails = gson.fromJson(salary, salarylist::class.java)
    }
    fun addleavedetails(leave:String){
        LeaveDetails = gson.fromJson(leave,Leavelist::class.java)
    }

}