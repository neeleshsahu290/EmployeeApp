package com.example.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login.`object`.Employees
import com.example.login.databinding.FragmentLeaveBinding


class LeaveFragment : Fragment() {

    private var _binding: FragmentLeaveBinding? = null


    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLeaveBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val size= Employees.Leave?.employee?.size
        if (size!! >0) {

            val first = Employees.Leave?.employee?.get(0)
            val ename = first?.ename
            val salary = first?.salary
            val taken_leave = first?.taken_leave
            val total_leave = first?.total_leaves

            binding.ename.text = ename
            binding.salary.text = salary
            binding.leaveTaken.text = taken_leave
            binding.totalLeaves.text = total_leave

        }
        return binding.root
    }


}