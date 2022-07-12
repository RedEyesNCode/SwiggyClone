package com.kotlinapp.swiggyclone.cart.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.base.BaseFragment
import com.kotlinapp.swiggyclone.cart.view.adapter.CartItemsAdapter
import com.kotlinapp.swiggyclone.cart.view.cartAddress.CartAddressFragment
import com.kotlinapp.swiggyclone.cart.view.cartViewModel.CartViewModel
import com.kotlinapp.swiggyclone.cart.view.model.Cart
import com.kotlinapp.swiggyclone.cart.view.model.Product
import com.kotlinapp.swiggyclone.databinding.FragmentCartBinding
import com.kotlinapp.swiggyclone.utils.FragmentUtils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private  lateinit var cartFragmentContext :CartFragment
    private lateinit var binding:FragmentCartBinding
    private lateinit var cartViewModel : CartViewModel
    private lateinit var adapter: CartItemsAdapter
    var carts = ArrayList<Cart>()
    var totalPayableAmount:Int = 0
    private lateinit var cartOrderIds:IntArray

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

        binding = FragmentCartBinding.inflate(inflater,container,false)
        initClicks()
        initCouroutines()
        setUpCartAdapter()

        return binding.root
    }

    private fun initCouroutines() {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        cartViewModel.isFailed.observe(viewLifecycleOwner, {
                error -> showToast(error)
        })
        cartViewModel.isConnecting.observe(viewLifecycleOwner, { isLoading ->
            if(isLoading){
                showLoader()
            }else{
                hideLoader()
            }
        })

        cartViewModel.getUserCart(getAccessToken(),getUserId().toInt())
        cartViewModel.getCartMutableLiveData.observe(viewLifecycleOwner,{
            response ->
            carts.addAll(response.cart)
            cartOrderIds = IntArray(carts.size)

            adapter.notifyDataSetChanged()
            for (index in response.cart.indices){
                totalPayableAmount += response.cart.get(index).product?.productData?.price!!
                carts.get(index).orderId?.let { cartOrderIds.set(index, it) }
            }
            binding.btnCompleteorder.setText("Complete Order \n (Payable amount) : $ $totalPayableAmount")
        })
    }

    private fun setUpCartAdapter() {
        adapter = CartItemsAdapter(requireContext(),carts)
        binding.recvCart.adapter =  adapter
        binding.recvCart.layoutManager = LinearLayoutManager(context)

    }

    private fun initClicks() {
        binding.btnCompleteorder.setOnClickListener {
            var cartAddressFragment = CartAddressFragment.newInstance(totalPayableAmount.toString(),cartOrderIds)
            FragmentUtils().addFragmentBackStack(requireFragmentManager(),R.id.cartcontainer,cartAddressFragment,CartAddressFragment::class.java.simpleName,true)

        }
        binding.back.setOnClickListener {

            fragmentManager?.popBackStack()
            activity?.finish()
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}