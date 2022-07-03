package com.kotlinapp.swiggyclone.cart.view.cartAddress

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.cart.view.cartPaymentFragment.CartPaymentFragment
import com.kotlinapp.swiggyclone.databinding.FragmentCartAddressBinding
import com.kotlinapp.swiggyclone.utils.FragmentUtils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CartAddressFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartAddressFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var orderIds: IntArray? = null
    private lateinit var binding:FragmentCartAddressBinding
    private lateinit var contextFragment:Context
    private var addressId:Int =0
    private var deliveryMethod:String = "DOOR_DELIVERY"
    private var orderTotal:String = ""
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contextFragment = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            orderIds = it.getIntArray(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartAddressBinding.inflate(inflater,container,false)
        initClicks()
        if(param1!=null){
            binding.tvTotalAmount.setText(" $ "+param1)
            orderTotal = param1.toString()
        }


        return binding.root
    }

    private fun initClicks() {
        binding.back.setOnClickListener { requireActivity().onBackPressed() }
        binding.btnProceed.setOnClickListener {
            var cartPaymentFragment = CartPaymentFragment.newInstance(addressId.toString(),orderTotal.toString(),deliveryMethod,orderIds!!)
            FragmentUtils().addFragmentBackStack(requireFragmentManager(),R.id.mainHomeContainer,cartPaymentFragment,CartAddressFragment::class.java.simpleName,true) }
        //COMMIT TEST
        binding.rbtnDoorDelivery.isChecked = true
        binding.rgroupDelivery.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId==R.id.rbtnDoorDelivery){
                deliveryMethod = "DOOR_DELIVERY"
            }else if(checkedId==R.id.rbtnPickup){
                deliveryMethod = "PICK_UP"

            }



        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CartAddressFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, orderIds: IntArray) =
            CartAddressFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putIntArray(ARG_PARAM2, orderIds)
                }
            }
    }
}