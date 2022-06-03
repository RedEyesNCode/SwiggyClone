package com.kotlinapp.swiggyclone.productDetail.singleProductView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.base.BaseFragment
import com.kotlinapp.swiggyclone.databinding.FragmentSingleProductBinding
import com.kotlinapp.swiggyclone.productDetail.viewmodel.ProductDetailViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SingleProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SingleProductFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding:FragmentSingleProductBinding
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

        binding = FragmentSingleProductBinding.inflate(inflater,container,false)

//        initCoroutines()

        try {
            binding.tvRestaurantName.text = param1

        }catch (e:Exception){
            e.printStackTrace()
            showToast(e.message.toString()
            )
        }


        return binding.root
    }

    private fun initCoroutines() {
        restaurantDetailViewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)

        restaurantDetailViewModel.isFailed.observe(viewLifecycleOwner, Observer {
                error -> showToast(error)
        })
        restaurantDetailViewModel.isConnecting.observe(viewLifecycleOwner, Observer { isLoading ->
            if(isLoading){
                showLoader()
            }else{
                hideLoader()
            }
        })

        restaurantDetailViewModel.getAllProducts(getAccessToken())

        restaurantDetailViewModel.allProductsResponseModelMutableLiveData.observe(this, Observer {
                response ->


            // issue is to feed the data accordingly in the fragment with the viewpager position into consideration.
        })


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SingleProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SingleProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}