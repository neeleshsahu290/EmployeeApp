package com.example.login.dataclass

data class Leavelist(
    val employee: ArrayList<Employe>?=null
)
data class Employe(
    val eid: String,
    val ename: String,
    val salary: String,
    val taken_leave: String,
    val total_leaves: String
)