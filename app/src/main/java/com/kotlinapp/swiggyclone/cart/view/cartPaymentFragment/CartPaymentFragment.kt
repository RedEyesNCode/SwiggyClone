package com.kotlinapp.swiggyclone.cart.view.cartPaymentFragment

import android.R.attr
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.base.BaseFragment
import com.kotlinapp.swiggyclone.cart.view.cartViewModel.CartViewModel
import com.kotlinapp.swiggyclone.cart.view.model.PlaceOrderBody
import com.kotlinapp.swiggyclone.databinding.BottomSheetCardBinding
import com.kotlinapp.swiggyclone.databinding.FragmentCartPaymentBinding
import com.kotlinapp.swiggyclone.homeScreen.view.HomeActivity
import com.kotlinapp.swiggyclone.sharedPreferences.AppSession
import com.kotlinapp.swiggyclone.sharedPreferences.Constant
import java.util.*


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
class CartPaymentFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var addressId: String? = null
    private var orderTotal: String? = null
    private lateinit var orderIds:IntArray
    private var deliveryMethod: String? = null
    private lateinit var  binding:FragmentCartPaymentBinding
    val UPI_PAYMENT = 0
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var contextFragment:Context
    private lateinit var cartViewModel: CartViewModel
    var placeOrderBody:PlaceOrderBody = PlaceOrderBody()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contextFragment = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            addressId = it.getString(ARG_PARAM1)
            orderTotal = it.getString(ARG_PARAM2)
            deliveryMethod = it.getString(ARG_PARAM3)
            orderIds = it.getIntArray("ORDER_IDS")!!
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartPaymentBinding.inflate(inflater,container,false)
        placeOrderBody.amount = orderTotal?.toInt()
        var name = AppSession(contextFragment).getString(Constant().USER_ID)

        placeOrderBody.orderName = "TESTING"
        placeOrderBody.orderStatus="Placed"
        placeOrderBody.addressId=3
        placeOrderBody.userId = name?.toInt()

        placeOrderBody.customerName=name
        initClicks()
        initCoroutines()
        return binding.root
    }

    private fun initCoroutines() {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        cartViewModel.isFailed.observe(viewLifecycleOwner) { error ->
            showToast(error)
        }
        cartViewModel.isConnecting.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                showLoader()
            } else {
                hideLoader()
            }
        }
        cartViewModel.commonStatusMessageResponse.observe(viewLifecycleOwner) {
            if(it.status!!.contains("success")){
                var intent = Intent(contextFragment,HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }



        }


    }

    private fun initClicks() {
        if(orderTotal!=null){
            binding.tvTotalAmount.text = "$ "+orderTotal
        }
        binding.btnPayment.text = "PAY $ $orderTotal"

        binding.tvOrderIds.text = orderIds.joinToString(",","Order Id: ")

        binding.btnPayment.setOnClickListener {

            if(binding.rbCardPayment.isChecked){
                placeOrderBody.provider = "CARD"

                showCardBottomSheet()
            }else{
                if(orderTotal!=null){
                    initliazeUpiPayment(orderTotal!!,"yourname@bankname","Realise Reality ;)")
                    placeOrderBody.provider = "UPI"

                }

            }


        }
        binding.back.setOnClickListener {
            fragmentManager?.popBackStack()
        }


    }

    private fun initliazeUpiPayment(amount:String,upiId:String ,note:String) {
        val uri: Uri = Uri.parse("upi://pay").buildUpon()
            .appendQueryParameter("pa", upiId)
            .appendQueryParameter("pn", "RedEyesNCode")
            .appendQueryParameter("tn", note)
            .appendQueryParameter("am", amount)
            .appendQueryParameter("cu", "INR")
            .build()
        val upiPayIntent = Intent(Intent.ACTION_VIEW)
        upiPayIntent.data = uri

        // will always show a dialog to user to choose an app

        // will always show a dialog to user to choose an app
        val chooser = Intent.createChooser(upiPayIntent, "Pay with")

        // check if intent resolves

        // check if intent resolves
        if (null != chooser.resolveActivity(requireActivity().packageManager)) {
            startActivityForResult(chooser, UPI_PAYMENT)
        } else {
            Toast.makeText(
                requireContext(),
                "No UPI app found, please install one to continue",
                Toast.LENGTH_SHORT
            ).show()
        }



    }

    private fun showCardBottomSheet() {
        bottomSheetDialog = BottomSheetDialog(contextFragment,R.style.BottomSheetDialogTheme)
        var bottomSheetCardBinding = BottomSheetCardBinding.inflate(LayoutInflater.from(contextFragment))
        bottomSheetDialog.setContentView(bottomSheetCardBinding.root)
        bottomSheetCardBinding.closeBottomSheet.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        // TASK 1 :  Proceed with proper card fields validation
        // TASK 2 : Once we are good with validation , proceed with the savedCardRadioButton, check
        // Task 3 : Implement the logic for turned off the switch if the user's entered card is already save n info the user.


        bottomSheetCardBinding.btnDoPayment.setOnClickListener {
            val cardNumber: String = bottomSheetCardBinding.edCardNumber.text.toString()
            val cardHolderName: String = bottomSheetCardBinding.edCardHolderName.text.toString()
            val cardMonthYear: String = bottomSheetCardBinding.edMmYy.text.toString()
            val cardCVC: String = bottomSheetCardBinding.edCvv.text.toString()
            if(performCardValidation(cardNumber, cardHolderName, cardMonthYear, cardCVC)){
                // Call the api for the place order.

                // TODO WARNING CALLING API IN LOOP.
                //TODO FIND AN EFFICEINT SOLUTION FOR PROVIDE THE ORDER ID'S DO NOT CALL API'S IN LOOP.
                for (index in orderIds.indices){
                    placeOrderBody.orderId = orderIds[index]
                    cartViewModel.placeOrder(getAccessToken(),placeOrderBody)
                }

            }
        }

        bottomSheetDialog.show()


    }
    private fun performCardValidation(
        cardNumber: String,
        cardHolderName: String,
        cardMonthYear: String,
        cardCVC: String
    ): Boolean {

        // We need to also add the stripe card validation procedure.
        if (cardNumber.isEmpty()) {
            showToast("Please enter your card number.")
            return false
        } else if (cardHolderName.isEmpty()) {
            showToast("Please enter the card holder name.")
            return false
        } else if (cardMonthYear.isEmpty() || cardMonthYear.length < 4) {
            if (cardMonthYear.isEmpty()) {
                showToast("Please enter the card expiry date.")
            } else {
                showToast("Please enter the card expiry in MM/YY format.")
                return false
            }
        } else if (cardCVC.isEmpty() || cardCVC.length < 3) {
            if (cardCVC.isEmpty()) {
                showToast("Please enter card cvc.")
            } else {
                showToast("Please enter correct card cvc.")
            }
            return false
        } else {
            return true
        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            UPI_PAYMENT -> if (Activity.RESULT_OK === resultCode || resultCode === 11) {
                if (attr.data != null) {
                    val trxt: String? = data?.getStringExtra("response")
                    Log.d("UPI", "onActivityResult: $trxt")
                    val dataList: ArrayList<String> = ArrayList()
                    if(trxt!=null){
                        dataList.add(trxt)

                    }
                    upiPaymentDataOperation(dataList)
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null")
                    val dataList: ArrayList<String> = ArrayList()
                    dataList.add("nothing")
                    upiPaymentDataOperation(dataList)
                }
            } else {
                Log.d(
                    "UPI",
                    "onActivityResult: " + "Return data is null"
                ) //when user simply back without payment
                val dataList: ArrayList<String> = ArrayList()
                dataList.add("nothing")
                upiPaymentDataOperation(dataList)
            }
        }
    }

    private fun upiPaymentDataOperation(data: ArrayList<String>) {
        if (isConnectionAvailable(requireContext())) {
            var str = data[0]
            Log.d("UPIPAY", "upiPaymentDataOperation: $str")
            var paymentCancel = ""
            if (str == null) str = "discard"
            var status = ""
            var approvalRefNo = ""
            val response = str.split("&").toTypedArray()
            for (i in response.indices) {
                val equalStr = response[i].split("=").toTypedArray()
                if (equalStr.size >= 2) {
                    if (equalStr[0].lowercase(Locale.getDefault()) == "Status".lowercase(Locale.getDefault())) {
                        status = equalStr[1].lowercase(Locale.getDefault())
                    } else if (equalStr[0].lowercase(Locale.getDefault()) == "ApprovalRefNo".lowercase(
                            Locale.getDefault()
                        ) || equalStr[0].lowercase(Locale.getDefault()) == "txnRef".lowercase(
                            Locale.getDefault()
                        )
                    ) {
                        approvalRefNo = equalStr[1]
                    }
                } else {
                    paymentCancel = "Payment cancelled by user."
                }
            }
            if (status == "success") {
                //Code to handle successful transaction here.
                Toast.makeText(requireContext(), "Transaction successful.", Toast.LENGTH_SHORT)
                    .show()
                // Call the api here  for the place order.

                Log.d("UPI", "responseStr: $approvalRefNo")
            } else if ("Payment cancelled by user." == paymentCancel) {
                Toast.makeText(requireContext(), "Payment cancelled by user.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Transaction failed.Please try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                requireContext(),
                "Internet connection is not available. Please check and try again",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun isConnectionAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val netInfo = connectivityManager.activeNetworkInfo
            if (netInfo != null && netInfo.isConnected
                && netInfo.isConnectedOrConnecting
                && netInfo.isAvailable
            ) {
                return true
            }
        }
        return false
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
        fun newInstance(addressId: String, orderTotal: String, deliveryMethod:String,orderIds:IntArray) =
            CartPaymentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, addressId)
                    putString(ARG_PARAM2, orderTotal)
                    putString(ARG_PARAM3, deliveryMethod)
                    putIntArray("ORDER_IDS",orderIds)
                }
            }
    }
}