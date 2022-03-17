package com.example.login.dataclass

data class loginModelClass(var username:String,var password:String,val msg: String? = null, val eid:String?= null)
data class UserResponse(val msg:String?= null ,val eid:String? = null)
data class Employeedetail(var eid: Int?= null)
