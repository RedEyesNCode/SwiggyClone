package com.kotlinapp.swiggyclone.searchFood.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinapp.swiggyclone.databinding.OffersListBinding
import com.kotlinapp.swiggyclone.searchFood.model.StaticFoodOffersModel

class FoodOfferAdapter(var context: Context, var list: ArrayList<StaticFoodOffersModel>):RecyclerView.Adapter<FoodOfferAdapter.MyViewHolder>() {
    private var offersListBinding: OffersListBinding?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        offersListBinding = OffersListBinding.inflate(LayoutInflater.from(context))

        return MyViewHolder(offersListBinding!!)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(offersListBinding){


            }
        }


    }

    override fun getItemCount(): Int {


        return list.size
    }

    inner class MyViewHolder(var offersListBinding: OffersListBinding):RecyclerView.ViewHolder(offersListBinding.root)


}