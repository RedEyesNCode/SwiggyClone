package com.kotlinapp.swiggyclone.cart.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinapp.swiggyclone.cart.view.model.Cart
import com.kotlinapp.swiggyclone.cart.view.model.GetCartResponseModel
import com.kotlinapp.swiggyclone.databinding.CartItemBinding

class CartItemsAdapter(var context: Context,var products:ArrayList<Cart>) :RecyclerView.Adapter<CartItemsAdapter.MyViewHolder>() {

    private lateinit var binding: CartItemBinding



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CartItemBinding.inflate(LayoutInflater.from(context))

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var product = products.get(position)
        with(holder){
            with(binding){
                // NULL SAFETY CHECK IN KOTLIN
                Glide.with(context).load(product.product?.productData?.productImage).into(foodImage)
                tvItemName.text = product.product?.productData?.dishName
                tvItemPrice.text = "$ "+product.product?.productData?.price.toString()

            }
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }

    class MyViewHolder(var binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root)
}