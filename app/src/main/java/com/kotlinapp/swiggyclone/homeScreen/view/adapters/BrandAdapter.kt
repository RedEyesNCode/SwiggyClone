package com.kotlinapp.swiggyclone.homeScreen.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinapp.swiggyclone.databinding.SwiggyServiceListBinding
import com.kotlinapp.swiggyclone.homeScreen.models.Brands

class BrandAdapter(var context: Context, var brandsList:ArrayList<Brands>):RecyclerView.Adapter<BrandAdapter.MyViewHolder>() {

    private lateinit var swiggyServiceListBinding: SwiggyServiceListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        swiggyServiceListBinding = SwiggyServiceListBinding.inflate(LayoutInflater.from(context))

        return MyViewHolder(swiggyServiceListBinding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        with(holder){
            with(swiggyServiceListBinding){
                var brands:Brands = brandsList.get(position)
                tvSwiggyService.text = brands.restaurantName

            }
        }

    }

    override fun getItemCount(): Int {

        return brandsList.size
    }

    class MyViewHolder(swiggyServiceListBinding: SwiggyServiceListBinding):RecyclerView.ViewHolder(swiggyServiceListBinding.root){}

}