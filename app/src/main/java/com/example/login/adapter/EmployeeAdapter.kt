package com.example.login.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.activity.EmployeeDetailsActivity
import com.example.login.activity.LoginActivity
import com.example.login.databinding.EmployeelistBinding
import com.example.login.dataclass.EmployeedataItem

class EmployeeAdapter(val context: Context,private val EmployeeList: ArrayList<EmployeedataItem>?):RecyclerView.Adapter<EmployeeAdapter.EmployeeViewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.employeelist,parent,false)
        return EmployeeViewholder(view)

    }

    override fun onBindViewHolder(holder: EmployeeViewholder, position: Int) {
        val Einfo = EmployeeList?.get(position)
        holder.binding.ename.text= Einfo?.ename.toString()
        holder.binding.depertment.text= Einfo?.dept_name.toString()
        holder.binding.gender.text= Einfo?.gender.toString()
        holder.binding.doj.text= Einfo?.doj.toString()
        holder.binding.location.text= Einfo?.loc_name.toString()
        holder.binding.sslary.text= Einfo?.salary.toString()
        holder.binding.btnmoredetails.setOnClickListener {
            val intent = Intent(context, EmployeeDetailsActivity::class.java)
            intent.putExtra("eid", Einfo?.eid.toString())
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
       return EmployeeList?.size!!

    }

    class EmployeeViewholder (itemView: View) :
        RecyclerView.ViewHolder(itemView){
        val binding : EmployeelistBinding = EmployeelistBinding.bind(itemView)

    }


}