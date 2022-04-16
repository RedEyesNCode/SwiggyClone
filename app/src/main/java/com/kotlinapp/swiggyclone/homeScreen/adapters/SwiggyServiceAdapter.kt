package com.kotlinapp.swiggyclone.homeScreen.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinapp.swiggyclone.databinding.SwiggyServiceListBinding

class SwiggyServiceAdapter(private var list: List<String>, private var context: Context) : RecyclerView.Adapter<SwiggyServiceAdapter.MyViewHolder>() {
    private lateinit var swiggyServiceListBinding:SwiggyServiceListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        swiggyServiceListBinding = SwiggyServiceListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(swiggyServiceListBinding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        with(holder){
            with(swiggyServiceListBinding){
                tvSwiggyService.text = list.get(position)


            }

        }


    }

    override fun getItemCount(): Int {


        return list.size


    }

    inner class MyViewHolder(var swiggyServiceListBinding: SwiggyServiceListBinding) : RecyclerView.ViewHolder(swiggyServiceListBinding.root){

    }
}