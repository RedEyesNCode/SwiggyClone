package com.kotlinapp.swiggyclone.homeScreen.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.base.BaseFragment
import com.kotlinapp.swiggyclone.databinding.FragmentFoodBinding
import com.kotlinapp.swiggyclone.homeScreen.models.Restaurants
import com.kotlinapp.swiggyclone.homeScreen.view.adapters.RestaurantsAdapter
import com.kotlinapp.swiggyclone.homeScreen.viewModel.HomeViewModel
import com.kotlinapp.swiggyclone.productDetail.FoodDetailFragment
import com.kotlinapp.swiggyclone.smoothieKotlin.repository.AppRepository
import com.kotlinapp.swiggyclone.smoothieKotlin.viewModelFactory.ViewModelProviderFactory
import com.kotlinapp.swiggyclone.utils.FragmentUtils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodFragment : BaseFragment() , RestaurantsAdapter.onClicked {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding:FragmentFoodBinding
    private lateinit var contextFragment: Context
    private lateinit var homeViewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contextFragment = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCouroutines()

    }

    private fun initCouroutines() {
        var appRepository = AppRepository()
        var factory = ViewModelProviderFactory(requireActivity().application,appRepository)
        homeViewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)
        homeViewModel.callHomeapi(getAccessToken())

        homeViewModel.homeResponse.observe(viewLifecycleOwner, Observer { event ->
            if(event.peekContent().data!=null){
                // Update the UI, set the adapter
                setAdapter(event.peekContent().data?.restaurants)
            }else{
                showToast("Oops something went wrong !")
            }
        })


    }
    override fun onViewDetails(position: Int, restaurantId: Int) {
        FragmentUtils().replaceFragmentBackStack(requireFragmentManager(),R.id.mainHomeContainer,
            FoodDetailFragment.newInstance(restaurantId.toString(),""),
            FoodDetailFragment::class.java.simpleName,true)
    }
    private fun setAdapter(restaurants: ArrayList<Restaurants>?) {

        binding.recvFoods.adapter = RestaurantsAdapter(contextFragment,restaurants!!,this)
        binding.recvFoods.layoutManager = LinearLayoutManager(contextFragment,LinearLayoutManager.HORIZONTAL,false)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodBinding.inflate(inflater,container,false)
        initClicks()
        return binding.root


    }

    private fun initClicks() {


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FoodFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FoodFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}