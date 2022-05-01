package com.kotlinapp.swiggyclone.searchFood

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlinapp.swiggyclone.databinding.FragmentSearchFoodBinding
import com.kotlinapp.swiggyclone.homeScreen.models.AllRestaurantsResponseData
import com.kotlinapp.swiggyclone.searchFood.adapter.FoodOfferAdapter
import com.kotlinapp.swiggyclone.searchFood.adapter.FoodSearchAdapter
import com.kotlinapp.swiggyclone.searchFood.model.StaticFoodOffersModel
import com.kotlinapp.swiggyclone.searchFood.model.StaticFoodSearchModel
import com.kotlinapp.swiggyclone.searchFood.viewModel.SearchFoodViewModel
import com.kotlinapp.swiggyclone.sharedPreferences.AppSession
import com.kotlinapp.swiggyclone.sharedPreferences.Constant

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
    private lateinit var foodSearchAdapter: FoodSearchAdapter
    private var foodOfferAdapter:FoodOfferAdapter?=null
    private lateinit var searchFoodViewModel: SearchFoodViewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchFoodViewModel = this!!.run {
            ViewModelProvider(this).get(SearchFoodViewModel::class.java)

        }
        initView()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchFoodBinding.inflate(inflater,container,false)
        //Adding the items to the adapter


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFoodFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun initView(){
        var accessToken = AppSession(contextFragment).getInstance(contextFragment).getValue(Constant().ACCESS_TOKEN,contextFragment)

        searchFoodViewModel.getAllRestaurants(accessToken!!,contextFragment)
        searchFoodViewModel.allRestaurantsResponseDataMutableLiveData.observe(this,{
            if (it.code==200 && it.status!!.contains("success")){
                setAdapter(it)
            }
        })
    }
    fun setAdapter(allRestaurantsResponseData: AllRestaurantsResponseData){
        var arrayList = allRestaurantsResponseData.data
//        binding.recvRestaurants.adapter =
//        <<START FROM HERE !>>


    }
}