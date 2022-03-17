package com.example.login.network

import com.example.login.dataclass.loginModelClass
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("Android_API/Login.php")
    fun login(@Body loginModelClass: loginModelClass):Call<loginModelClass>


    @POST("Android_API/employee_info.php")
     fun getEmploee(@Body body: String?): Call<String?>

    @POST("Android_API/employee_salary.php")
    fun getSalary(@Body body: String?): Call<String?>

    @POST("Android_API/employee_leave.php")
    fun getLeave(@Body body: String?): Call<String?>



}