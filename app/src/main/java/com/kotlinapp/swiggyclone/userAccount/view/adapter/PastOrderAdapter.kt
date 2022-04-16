package com.kotlinapp.swiggyclone.userAccount.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinapp.swiggyclone.databinding.PastOrderListBinding
import com.kotlinapp.swiggyclone.userAccount.model.PastOrderResponseData
import com.kotlinapp.swiggyclone.userAccount.model.PastOrders

//ADAPTER PRIMARY CONSTRUCTOR CAN INCLUDE THE VAR KEYWORD
class PastOrderAdapter(var context: Context, var pastOrders:ArrayList<PastOrders>):RecyclerView.Adapter<PastOrderAdapter.MyViewHolder>() {

    private lateinit var binding: PastOrderListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        binding = PastOrderListBinding.inflate(LayoutInflater.from(context),parent,false)

        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            with(holder) {
                with(binding) {
                    var pastOrder = pastOrders.get(position)

                    tvProductName.text = pastOrder.restaurantName?:""
                    tvLocation.text = pastOrder.location?:""
                    tvOrderTotal.text = pastOrder.orderTotal.toString()

                }
            }




    }

    override fun getItemCount(): Int {
        return pastOrders.size
    }

    class MyViewHolder(binding:PastOrderListBinding):RecyclerView.ViewHolder(binding.root)

}