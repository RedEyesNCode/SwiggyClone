package com.kotlinapp.swiggyclone.homeScreen.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Binder
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.auth.viewModel.LoginViewModel
import com.kotlinapp.swiggyclone.base.BaseFragment
import com.kotlinapp.swiggyclone.cart.view.CartFragment
import com.kotlinapp.swiggyclone.cart.view.CartMainActivity
import com.kotlinapp.swiggyclone.databinding.BuyerMenuBinding
import com.kotlinapp.swiggyclone.databinding.FragmentHomeBinding
import com.kotlinapp.swiggyclone.homeScreen.adapters.FoodsCategoryAdapter
import com.kotlinapp.swiggyclone.homeScreen.models.Brands
import com.kotlinapp.swiggyclone.homeScreen.models.HomeResponse
import com.kotlinapp.swiggyclone.homeScreen.models.Restaurants
import com.kotlinapp.swiggyclone.homeScreen.view.adapters.BrandAdapter
import com.kotlinapp.swiggyclone.homeScreen.view.adapters.RestaurantsAdapter
import com.kotlinapp.swiggyclone.homeScreen.view.fragments.*
import com.kotlinapp.swiggyclone.homeScreen.viewModel.HomeViewModel
import com.kotlinapp.swiggyclone.sharedPreferences.AppSession
import com.kotlinapp.swiggyclone.sharedPreferences.Constant
import com.kotlinapp.swiggyclone.smoothieKotlin.repository.AppRepository
import com.kotlinapp.swiggyclone.smoothieKotlin.viewModel.LoginViewModelCoroutines
import com.kotlinapp.swiggyclone.smoothieKotlin.viewModelFactory.ViewModelProviderFactory
import com.kotlinapp.swiggyclone.utils.FragmentUtils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val tagFragment:String = HomeFragment.javaClass.simpleName
    private lateinit var binding:FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    var contextFragment: Context? = null
    private lateinit var tabAdapter:FoodsCategoryAdapter
    var datalist:ArrayList<Fragment> = arrayListOf(FoodFragment(), SnacksFragment(),
        ItalianFragment(), SaucesFragment(),SouthIndianFragment()
    )

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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        initNavigationView()
        initClicks()
        initClicksSideMenu()
        initTabsViewPager()

        // changes according to coroutines
        // Create two methods
        initCoroutines() // INTIALIZE THE REPO AND OTHER STUFF
        attachObserversCouroutines() // SEPERATE THREAD WAITING FOR THE RESPONSE FROM THE API.


        return binding.root
    }

    private fun attachObserversCouroutines() {
        homeViewModel.homeResponse.observe(viewLifecycleOwner, Observer { event ->

            var resourceData = event.peekContent().data

            if(resourceData==null){
                showLoader()

            }else{
                hideLoader()
                if(event.peekContent().data?.code==200 && event.peekContent().data?.status!!.contains("success")){
                    // UPDATE THE UI ACCORDING
                    if(event.peekContent().data!=null){
                        updateUI(event.peekContent().data!!)
                    }else{
                        showToast("Oops something went wrong !")
                    }


                }else{
                    Toast.makeText(contextFragment,"Oops Something Went Wrong.", Toast.LENGTH_SHORT).show()

                }


            }





        })


    }

    private fun updateUI(data: HomeResponse) {

    }

    private fun initCoroutines() {
        //INTIALIZE THE REPO
        val repository = AppRepository()
        // GET THE VIEW-MODEL FACTORY INTO THE PLAY.
        var factory = ViewModelProviderFactory(requireActivity().application,repository)
        homeViewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)

        var stringAccessToken  = AppSession(contextFragment!!).getValue(Constant().ACCESS_TOKEN,contextFragment!!)

        // Calling the home api as soon as user enters.
        homeViewModel.callHomeapi(stringAccessToken!!)



    }

    private fun initTabsViewPager() {

        tabAdapter = FoodsCategoryAdapter(childFragmentManager,contextFragment,datalist)
        binding.foodsViewpager.adapter = tabAdapter
        binding.tablayout.setupWithViewPager(binding.foodsViewpager)
        binding.foodsViewpager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                binding.tablayout
            )
        )
        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.foodsViewpager.setCurrentItem(tab.position)

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }
    override fun onResume() {
        super.onResume()
        initTabsViewPager()
    }

    private fun initClicksSideMenu() {
        var buyerMenuView = binding.navview.getHeaderView(0)
        var buyerMenuBinding = BuyerMenuBinding.bind(buyerMenuView)
        buyerMenuBinding.btnSignout.setOnClickListener {
            AppSession(contextFragment!!).getInstance(contextFragment!!).clearAll()
            activity?.finish()
        }



    }

    private fun initNavigationView() {
        var toggle:ActionBarDrawerToggle
        toggle = ActionBarDrawerToggle(activity,binding.mainDrawerLayout,android.R.string.ok,android.R.string.ok)
        binding.mainDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

    }

    private fun initClicks() {
        binding.btnMenu.setOnClickListener {
            binding.mainDrawerLayout.openDrawer(GravityCompat.START)

        }
        binding.btnCart.setOnClickListener {
            //OPEN THE CART FRAGMENT.
            var cartIntent = Intent(contextFragment,CartMainActivity::class.java)
            startActivity(cartIntent)

        }

    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}