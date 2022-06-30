package com.kotlinapp.swiggyclone.productDetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlinapp.swiggyclone.base.BaseFragment
import com.kotlinapp.swiggyclone.cart.view.CartFragment
import com.kotlinapp.swiggyclone.databinding.FragmentFoodDetailBinding
import com.kotlinapp.swiggyclone.productDetail.adapter.ProductAdapter
import com.kotlinapp.swiggyclone.productDetail.model.Datum
import com.kotlinapp.swiggyclone.productDetail.viewmodel.ProductDetailViewModel
import com.kotlinapp.swiggyclone.sharedPreferences.AppSession
import com.kotlinapp.swiggyclone.sharedPreferences.Constant
import com.kotlinapp.swiggyclone.utils.CommonInteractiveDialog


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodDetailFragment : BaseFragment() , ProductAdapter.onClicked,CommonInteractiveDialog.onClick {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding:FragmentFoodDetailBinding
    private lateinit var restaurantDetailViewModel : ProductDetailViewModel
    private lateinit var popup :PopupMenu
    private  var productIdUser: Int = 0

    override fun onViewDetails(position: Int, productId: Int) {
        showAddCartDialog(productId)

    }

    override fun onAddtoCart(position: Int, productId: Int) {
        // Call the api for add to cart
        productIdUser = productId
showAddCartDialog(productId)
    }

    fun addtoCart(productId: Int){
        var userID  = AppSession(requireContext()).getValue(Constant().USER_ID,requireContext())
        restaurantDetailViewModel.addtoCart(getAccessToken(),userID!!.toInt(),param1!!.toInt(),productIdUser)



    }

    override fun onPositive() {
        var userID  = AppSession(requireContext()).getValue(Constant().USER_ID,requireContext())
        restaurantDetailViewModel.addtoCart(getAccessToken(),userID!!.toInt(),param1!!.toInt(),productIdUser)

    }

    override fun onNegative() {
        // Do not do anything just dissmiss the dialog.
    }

    private fun showAddCartDialog(productId: Int) {
        // Setting the product user id when the adapter gets notified.
        productIdUser= productId
        var commonInteractiveDialog = CommonInteractiveDialog(requireContext())
        commonInteractiveDialog.CommonDialogBox(requireContext(),this)
        commonInteractiveDialog.showCommonDialog("Are you sure you want to add this item to cart ?","Add to Cart ?")
    }

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
       popup = PopupMenu(context, binding.filter)
        initCoroutines()
        initClicks()


        return binding.root
    }

    private fun initCoroutines() {
        restaurantDetailViewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)

        restaurantDetailViewModel.isFailed.observe(viewLifecycleOwner, {
            error -> showToast(error)
        })
        restaurantDetailViewModel.isConnecting.observe(viewLifecycleOwner, { isLoading ->
            if(isLoading){
                showLoader()
            }else{
                hideLoader()
            }
        })
        restaurantDetailViewModel.commonStatusMessageResponseMutableLiveData.observe(viewLifecycleOwner,
            {
                response ->

                showToast(response.message!!)

            })

        try {
            restaurantDetailViewModel.getRestaurantDetails(getAccessToken(),param1!!.toInt())
        }catch (e:Exception){
            showToast("Parsing Error !")
            e.printStackTrace()
        }
        restaurantDetailViewModel.restaurantDetailResponseMutableLiveData.observe(viewLifecycleOwner,
            {
                response ->

                if(response.code==200 && response.status!!.contains("success")){
                    binding.tvRestaurantName.text = response.data?.restaurantName
                    binding.tvDeliveryTime.text = response.data?.time
                    binding.tvRating.text = response.data?.rating.toString()
                    //Setting the location
                    binding.tvLocation.text = response.data?.location.toString()
                }


            })
        restaurantDetailViewModel.getAllProducts(getAccessToken())
        restaurantDetailViewModel.getAllProductTypes(getAccessToken())

        restaurantDetailViewModel.productTypeResponseMutableLiveData.observe(viewLifecycleOwner, {
            response ->
            for (productType in response)
            {
                popup.menu.add(productType.productType)
            }

        })

        restaurantDetailViewModel.allProductsResponseModelMutableLiveData.observe(viewLifecycleOwner,
            {
                response ->
                // FEED THIS DATA TO THE VIEWPAGER MADE FOR THE PRODUCTS.
                try {
                    updateViewpager(response.data)
                }catch (e:Exception){
                    e.printStackTrace()
                }
            })

        restaurantDetailViewModel.commonStatusMessageResponseMutableLiveData.observe(viewLifecycleOwner,{

            response ->
            Toast.makeText(requireContext(),response.message, Toast.LENGTH_SHORT).show()

        })
    }

    private fun updateViewpager(data: ArrayList<Datum>) {
        binding.recvProducts.adapter = ProductAdapter(requireContext(),data,this)
        binding.recvProducts.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)




    }

    private fun initClicks() {
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()

        }
        binding.filter.setOnClickListener {
            //Inflating the Popup using xml file
            //Inflating the Popup using xml file
            popup.getMenuInflater()
                .inflate(com.kotlinapp.swiggyclone.R.menu.food_filter_menu, popup.getMenu())

            //registering popup with OnMenuItemClickListener

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

                showToast(item?.itemId.toString())
                true
            })

            popup.show() //showing popup menu


        }
        binding.wishList.setOnClickListener {
            addFragmentBackStackFullContainer(CartFragment(),CartFragment::class.java.simpleName,true)
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