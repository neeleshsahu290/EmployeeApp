package com.example.login.dataclass

data class Employee(
    val earning_salary: String,
    val eid: String,
    val ename: String,
    val present_days: String,
    val salary: String
)
data class salarylist(
    val employee: ArrayList<Employee>
)