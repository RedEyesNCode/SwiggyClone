package com.kotlinapp.swiggyclone.homeScreen.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinapp.swiggyclone.databinding.TopPicksListBinding
import com.kotlinapp.swiggyclone.homeScreen.models.Restaurants
import java.util.ArrayList

class RestaurantsAdapter(var context: Context, var restaurants: ArrayList<Restaurants>) :RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder>(){

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

               /* if(restaurantName!!.length>10){
                    tvRestaurantName.text = restaurantName.substring(0,9)+" \n"+restaurantName.substring(10,restaurantName.length)

                    //Used this code for solving the long text of the restaurants.


                }*/
                tvRestaurantName.text = restaurantName
                tvDeliveryTime.text = deliveryTime




            }

        }



    }

    override fun getItemCount(): Int {

       return restaurants.size
    }

    class MyViewHolder(topPicksListBinding: TopPicksListBinding):RecyclerView.ViewHolder(topPicksListBinding.root)

}