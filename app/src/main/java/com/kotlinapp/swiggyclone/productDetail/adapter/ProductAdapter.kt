package com.kotlinapp.swiggyclone.productDetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinapp.swiggyclone.databinding.FoodItemBinding
import com.kotlinapp.swiggyclone.databinding.ProductDetailItemBinding
import com.kotlinapp.swiggyclone.homeScreen.models.Restaurants
import com.kotlinapp.swiggyclone.productDetail.model.Datum

class ProductAdapter(var context: Context, var restaurants: ArrayList<Datum>, var onClickedActivity: onClicked) :RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    private lateinit var topPicksListBinding: ProductDetailItemBinding
    private var  onClickedInterface: onClicked

    init {
        onClickedInterface=  onClickedActivity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        topPicksListBinding = ProductDetailItemBinding.inflate(LayoutInflater.from(context),parent,false);


        return MyViewHolder(topPicksListBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var product = restaurants.get(position)

        with(holder) {
            with(topPicksListBinding) {
                Glide.with(context).load(product.productImage).into(profileImg)
                tvRestaurantName.text = product.dishName
                tvPrice.text = product.price.toString()
                foodLayout.setOnClickListener {
                    product!!.menuId?.let { it1 -> onClickedInterface.onViewDetails(position, it1) }

                }
                btnAddtoCart.setOnClickListener {
                    product.menuId?.let { it1 -> onClickedInterface.onAddtoCart(position, it1) }

                }


            }

        }
    }


    override fun getItemCount(): Int {

        return restaurants.size
    }

    class MyViewHolder(topPicksListBinding: ProductDetailItemBinding): RecyclerView.ViewHolder(topPicksListBinding.root)
    interface onClicked{
        fun onViewDetails(position: Int, productId:Int)
        fun onAddtoCart(position: Int,productId: Int)
    }
}