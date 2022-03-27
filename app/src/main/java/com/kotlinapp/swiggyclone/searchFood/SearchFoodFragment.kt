package com.kotlinapp.swiggyclone.searchFood

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlinapp.swiggyclone.databinding.FragmentSearchFoodBinding
import com.kotlinapp.swiggyclone.searchFood.adapter.FoodOfferAdapter
import com.kotlinapp.swiggyclone.searchFood.adapter.FoodSearchAdapter
import com.kotlinapp.swiggyclone.searchFood.model.StaticFoodOffersModel
import com.kotlinapp.swiggyclone.searchFood.model.StaticFoodSearchModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFoodFragment : Fragment() , FoodSearchAdapter.onClicked  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var contextFragment: Context
    val tagFragment = SearchFoodFragment.javaClass.simpleName
    private lateinit var binding: FragmentSearchFoodBinding
    private var resturantList:ArrayList<StaticFoodSearchModel> = ArrayList<StaticFoodSearchModel>()
    private var offersList:ArrayList<StaticFoodOffersModel> = ArrayList<StaticFoodOffersModel>()
    private lateinit var foodSearchAdapter: FoodSearchAdapter
    private var foodOfferAdapter:FoodOfferAdapter?=null

    override fun onRestaurantClick(position: Int) {
        Toast.makeText(contextFragment,position.toString(),Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contextFragment=context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchFoodBinding.inflate(inflater,container,false)
        //Adding the items to the adapter

        setStaticData()
        setAdapter(resturantList,offersList)

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFoodFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFoodFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun setAdapter(arrayList: ArrayList<StaticFoodSearchModel>, arrayListOffers: ArrayList<StaticFoodOffersModel>){

        foodSearchAdapter = FoodSearchAdapter(contextFragment,arrayList,this)
        foodOfferAdapter = FoodOfferAdapter(contextFragment,arrayListOffers)
        binding.recvtopPicks.adapter = foodOfferAdapter
        binding.recvtopPicks.layoutManager = LinearLayoutManager(contextFragment,LinearLayoutManager.HORIZONTAL,false)
        binding.recvRestaurants.adapter = foodSearchAdapter
        binding.recvRestaurants.layoutManager = LinearLayoutManager(contextFragment)

    }
    fun setStaticData(){
        resturantList.add(StaticFoodSearchModel("McDonal's",4.1,"30-35 mins"," 400 for two","American"))
        resturantList.add(StaticFoodSearchModel("Apna Sweets",4.1,"25-35 mins"," 500 for two","Chinese , North Indian, Snacks, Sweets"))
        resturantList.add(StaticFoodSearchModel("Hotel Zakir",3.6,"40-45 mins"," 250 for two","Muglai, Biryani, North Indian"))
        resturantList.add(StaticFoodSearchModel("KFC ",4.0,"40-45 mins"," 400 for two","American,Snack,Biryani"))
        resturantList.add(StaticFoodSearchModel("Jhony Hot Dog-56 Dukan",4.4,"35-40 mins"," 150 for two","Indian,Snacks,Fast Food, Chinese"))

        offersList.add(StaticFoodOffersModel(40.2,"GRAB 40.2 % OFF On Fresh Flavors",1))
        offersList.add(StaticFoodOffersModel(60.0,"GRAB 60 % OFF ! Use Coupon 'TRYNEW' ",2))
        offersList.add(StaticFoodOffersModel(60.0,"GRAB 60 % OFF ! Use Coupon 'MISSEDYOU' ",3))
        offersList.add(StaticFoodOffersModel(40.0,"GRAB 40.0 % Use Couple 'JUMBO' ",4))


    }
}