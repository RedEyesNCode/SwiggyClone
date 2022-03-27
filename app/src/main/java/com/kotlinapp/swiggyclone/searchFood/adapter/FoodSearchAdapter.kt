package com.kotlinapp.swiggyclone.searchFood.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinapp.swiggyclone.databinding.RestuarantDetailListBinding
import com.kotlinapp.swiggyclone.searchFood.model.StaticFoodSearchModel

class FoodSearchAdapter(private var context: Context, private var list: ArrayList<StaticFoodSearchModel>,private var onClickedInterface:onClicked
):
    RecyclerView.Adapter<FoodSearchAdapter.MyViewHolder>() {

    private var restuarantDetailListBinding: RestuarantDetailListBinding?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        restuarantDetailListBinding = RestuarantDetailListBinding.inflate(LayoutInflater.from(context))
        return MyViewHolder(restuarantDetailListBinding!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(restuarantDetailListBinding){
                restuarantDetailListBinding.mainLayout.setOnClickListener {
                    onClickedInterface.onRestaurantClick(position)
                }

            }
        }

    }

    override fun getItemCount(): Int {

        return list.size

    }

    inner class MyViewHolder
        (var restuarantDetailListBinding: RestuarantDetailListBinding):RecyclerView.ViewHolder(restuarantDetailListBinding.root){        }

    public interface onClicked{
        fun onRestaurantClick (position:Int)
    }
}