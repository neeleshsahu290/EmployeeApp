package com.example.login.network

import android.util.Log
import com.example.login.`object`.Employees
import retrofit2.Response

class Networkcall {


    fun getemployyeDetails(eid:String):String?{
        val body = "{\n" +
                "\"eid\": $eid\n" +
                "}"
        var response : Response<String?>?  = null
        var msg =""

        try {
            val retrofitBuilder = RetrofitClient().getClient()
                ?.create(ApiService::class.java)
            val call = retrofitBuilder?.getEmploee(body)
            response = call?.execute()

            if (response!!.isSuccessful && response.body() != null) {
                val data = response.body().toString()
                Log.d("Success",response.message().toString())
                Log.d("succes","Employee=> "+response.body().toString())
                Employees.addEmployee(data)

            } else {
                Log.e("failure",response.message().toString())

            }}
        catch (e: Exception){
            Log.e("catch",e.message.toString())

        }
        msg=response!!.message()
        return msg



    }
    fun getSaalaryDetails(eid:String):String?{
        val body = "{\"eid\":\"$eid\",\n" +
                "\"date\":\"2022-03\"}"
        var response : Response<String?>?  = null
        var msg =""

        try {
            val retrofitBuilder = RetrofitClient().getClient()
                ?.create(ApiService::class.java)
            val call = retrofitBuilder?.getSalary(body)
            response = call?.execute()

            if (response!!.isSuccessful && response.body() != null) {
                val data = response.body().toString()
                Log.d("Success",response.message().toString())
                Log.d("succes","Salary=>" +response.body().toString())
                Employees.addsalary(data)
            } else {
                Log.d("failure",""+response.message().toString())

            }}
        catch (e: Exception){
            Log.d("catch",e.message.toString())

        }

        msg=response!!.message()
        return msg

    }
    fun getLeaveDetails(eid:String):String{
        val body = "{\n" +
                "\"eid\": $eid\n" +
                "}"

        var response : Response<String?>?  = null

        var msg =""


        try {
            val retrofitBuilder = RetrofitClient().getClient()
                ?.create(ApiService::class.java)
            val call = retrofitBuilder?.getLeave(body)
            response = call?.execute()
            if (response!!.isSuccessful && response.body() != null) {
                val data = response.body().toString()
                Log.d("Success",response.message().toString())
                Log.d("succes","Leave=> " +response.body().toString())

                Employees.addleave(data)

            } else {
                Log.d("failure",response.message().toString())
            }}
        catch (e: Exception){
            Log.d("catch",e.message.toString())
        }
        msg=response!!.message()
        return msg
    }

}