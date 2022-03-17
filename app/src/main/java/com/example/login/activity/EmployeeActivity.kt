package com.example.login.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.adapter.EmployeeAdapter
import com.example.login.`object`.Employees
import com.example.login.databinding.ActivityEmployeeBinding


class EmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeBinding
    lateinit var EmployeeRecyclerview : RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: EmployeeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title="Employee List"
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        EmployeeRecyclerview=binding.activityRecyclerview
        EmployeeRecyclerview.setHasFixedSize(true)
        layoutManager = LinearLayoutManager (this)
        val mDividerItemDecoration = DividerItemDecoration(
            EmployeeRecyclerview.context,
            (layoutManager as LinearLayoutManager).orientation
        )
        EmployeeRecyclerview.addItemDecoration(mDividerItemDecoration)
        recyclerAdapter = EmployeeAdapter(this@EmployeeActivity,Employees.employee?.employee)
        EmployeeRecyclerview.adapter = recyclerAdapter
        EmployeeRecyclerview.layoutManager =  layoutManager

    }
}