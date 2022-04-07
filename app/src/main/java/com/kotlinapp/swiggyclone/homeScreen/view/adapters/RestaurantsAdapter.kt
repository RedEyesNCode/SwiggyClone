package com.kotlinapp.swiggyclone.homeScreen.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinapp.swiggyclone.databinding.TopPicksListBinding
import com.kotlinapp.swiggyclone.homeScreen.models.Restaurants
import java.util.ArrayList

class RestaurantsAdapter(var context: Context, var restaurants: ArrayList<Restaurants>) :RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder>{

    private lateinit var topPicksListBinding: TopPicksListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        topPicksListBinding = TopPicksListBinding.inflate(LayoutInflater.from(context),parent,false);


        return MyViewHolder(topPicksListBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(topPicksListBinding){
                var restaurant:Restaurants = restaurants.get(position)
                var restaurantName = restaurant.restaurantName
                var resturantRating = restaurant.rating
                var deliveryTime = restaurant.deliveryTime
                tvRestaurantName.text = restaurantName
                tvDeliveryTime.text = deliveryTime




            }

        }



    }

    override fun getItemCount(): Int {

        restaurants.size
    }

    class MyViewHolder(topPicksListBinding: TopPicksListBinding):RecyclerView.ViewHolder(topPicksListBinding.root)

}