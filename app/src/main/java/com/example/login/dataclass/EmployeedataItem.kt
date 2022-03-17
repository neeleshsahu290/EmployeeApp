package com.example.login.dataclass

data class EmployeedataItem(
    val dept_name: String,
    val doj: String,
    val eid: String,
    var ename: String,
    val gender: String,
    val loc_name: String,
    val salary: String
)
data class employeelist  (    val employee: ArrayList<EmployeedataItem>)