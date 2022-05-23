package com.kotlinapp.swiggyclone.homeScreen.view

import android.content.Context
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
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.auth.viewModel.LoginViewModel
import com.kotlinapp.swiggyclone.databinding.FragmentHomeBinding
import com.kotlinapp.swiggyclone.homeScreen.models.Brands
import com.kotlinapp.swiggyclone.homeScreen.models.Restaurants
import com.kotlinapp.swiggyclone.homeScreen.view.adapters.BrandAdapter
import com.kotlinapp.swiggyclone.homeScreen.view.adapters.RestaurantsAdapter
import com.kotlinapp.swiggyclone.homeScreen.viewModel.HomeViewModel
import com.kotlinapp.swiggyclone.sharedPreferences.AppSession
import com.kotlinapp.swiggyclone.sharedPreferences.Constant

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val tagFragment:String = HomeFragment.javaClass.simpleName
    private lateinit var binding:FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    var contextFragment: Context? = null

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
        homeViewModel = this!!.run {
            ViewModelProvider(this).get(HomeViewModel::class.java)
        }
        initView()



    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        initNavigationView()
        initClicks()
        initClicksSideMenu()

        return binding.root
    }

    private fun initClicksSideMenu() {

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
    fun initView(){



        var accessToken : String? = AppSession(contextFragment!!).getValue(Constant().ACCESS_TOKEN,contextFragment!!)
        Log.i("HOMEFRAGMENT : TOKEN : ",accessToken!!)
        //API CALLED BELOW
    }



}