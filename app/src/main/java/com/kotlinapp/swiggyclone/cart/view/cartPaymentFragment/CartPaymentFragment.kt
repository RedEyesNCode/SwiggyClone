package com.kotlinapp.swiggyclone.cart.view.cartPaymentFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinapp.swiggyclone.databinding.FragmentCartPaymentBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Use the [CartPaymentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartPaymentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var addressId: String? = null
    private var orderTotal: String? = null
    private var deliveryMethod: String? = null
    private lateinit var  binding:FragmentCartPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            addressId = it.getString(ARG_PARAM1)
            orderTotal = it.getString(ARG_PARAM2)
            deliveryMethod = it.getString(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartPaymentBinding.inflate(inflater,container,false)
        initClicks()
        return binding.root
    }

    private fun initClicks() {
        if(orderTotal!=null){
            binding.tvTotalAmount.text = "$ "+orderTotal
        }

        binding.btnProceed.setOnClickListener {
            //Implementing soon.
        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param addressId Parameter 1.
         * @param orderTotal Parameter 2.
         * @return A new instance of fragment CartPaymentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(addressId: String, orderTotal: String, deliveryMethod:String) =
            CartPaymentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, addressId)
                    putString(ARG_PARAM2, orderTotal)
                    putString(ARG_PARAM3, deliveryMethod)
                }
            }
    }
}