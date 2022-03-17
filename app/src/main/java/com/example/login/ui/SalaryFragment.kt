package com.example.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login.`object`.Employees
import com.example.login.databinding.FragmentSalaryBinding


class SalaryFragment : Fragment() {
    private var _binding: FragmentSalaryBinding? = null


    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSalaryBinding.inflate(inflater, container, false)
        val root: View = binding.root
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
        // Inflate the layout for this fragment
        return root
    }

}