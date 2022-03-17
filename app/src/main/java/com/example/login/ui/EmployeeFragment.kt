package com.example.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login.activity.EmployeeActivity
import com.example.login.`object`.Employees
import com.example.login.databinding.FragmentEmployeeBinding


class EmployeeFragment : Fragment() {
    private var _binding: FragmentEmployeeBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        binding.loadmore.setOnClickListener {

            val intent = Intent(activity, EmployeeActivity::class.java)
            startActivity(intent)

        }
        val size= Employees.employee?.employee?.size

        if (size!!>1){
            binding.loadmore.visibility = View.VISIBLE
        }



        if (size >0) {
            val first = Employees.employee?.employee?.get(0)
            val ename = first?.ename
            val gender = first?.gender
            val doj = first?.doj
            val department = first?.dept_name
            val location = first?.loc_name
            val salary = first?.salary
            binding.ename.text = ename
            binding.gender.text = gender
            binding.doj.text = doj
            binding.depertment.text = department
            binding.location.text = location
            binding.sslary.text = salary
        }
        // Inflate the layout for this fragment
        return root

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}