package com.kotlinapp.swiggyclone.productDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.base.BaseFragment
import com.kotlinapp.swiggyclone.databinding.FragmentFoodDetailBinding
import com.kotlinapp.swiggyclone.homeScreen.view.adapters.RestaurantsAdapter
import com.kotlinapp.swiggyclone.productDetail.viewmodel.ProductDetailViewModel
import com.kotlinapp.swiggyclone.utils.FragmentUtils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodDetailFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding:FragmentFoodDetailBinding
    private lateinit var restaurantDetailViewModel : ProductDetailViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFoodDetailBinding.inflate(inflater,container,false)
        initCoroutines()
        initClicks()



        return binding.root
    }

    private fun initCoroutines() {
        restaurantDetailViewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)

        restaurantDetailViewModel.isFailed.observe(this, Observer {
            error -> showToast(error)
        })
        restaurantDetailViewModel.isConnecting.observe(this, Observer { isLoading ->
            if(isLoading){
                showLoader()
            }else{
                hideLoader()
            }
        })

        try {
            restaurantDetailViewModel.getRestaurantDetails(getAccessToken(),param1!!.toInt())
        }catch (e:Exception){
            showToast("Parsing Error !")
            e.printStackTrace()
        }
        restaurantDetailViewModel.restaurantDetailResponseMutableLiveData.observe(this, Observer {
            response ->

            if(response.code==200 && response.status!!.contains("success")){
                binding.tvRestaurantName.text = response.data?.restaurantName
                binding.tvDeliveryTime.text = response.data?.time
                binding.tvRating.text = response.data?.rating.toString()
            }


        })


    }

    private fun initClicks() {
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FoodDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FoodDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}