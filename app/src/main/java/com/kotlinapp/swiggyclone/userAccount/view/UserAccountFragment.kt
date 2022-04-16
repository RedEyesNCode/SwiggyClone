package com.kotlinapp.swiggyclone.userAccount.view

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
import com.kotlinapp.swiggyclone.databinding.FragmentUserAccountBinding
import com.kotlinapp.swiggyclone.homeScreen.viewModel.HomeViewModel
import com.kotlinapp.swiggyclone.sharedPreferences.AppSession
import com.kotlinapp.swiggyclone.sharedPreferences.Constant
import com.kotlinapp.swiggyclone.userAccount.view.adapter.PastOrderAdapter
import com.kotlinapp.swiggyclone.userAccount.viewModel.AccountViewModel
import com.kotlinapp.swiggyclone.utils.AppUtils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserAccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserAccountFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var tagFragment = UserAccountFragment.javaClass.simpleName
    var contextFragment : Context?=null
    private lateinit var binding: FragmentUserAccountBinding
    private lateinit var accountViewModel: AccountViewModel

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
        //THIS IS NEW FROM JAVA this!!.run
        accountViewModel = this!!.run {
            ViewModelProvider(this).get(AccountViewModel::class.java)
        }
        initView()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUserAccountBinding.inflate(inflater,container,false);


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserAccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserAccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun initView(){


        var accessToken = AppSession(contextFragment!!).getValue(Constant().ACCESS_TOKEN,contextFragment!!)
        var userId = AppSession(contextFragment!!).getValue(Constant().USER_ID,contextFragment!!)

        //HERE WE ARE TELLING KOTLIN THAT THIS TWO VALUES IE ACCESSTOKEN AND USER ID CANNOT BE NULL
        accountViewModel.getUserDetailsById(accessToken!!,userId!!.toInt())
        //CALLING THE PAST ORDER API
        accountViewModel.getUserPastOrderById(accessToken!!,userId!!.toInt())

        accountViewModel.pastOrderResponseDataMutableLiveData.observe(this, Observer {

            if(it!=null){
                if(it.pastOrders.size!=0){

                    binding.recvPastOrders.adapter = PastOrderAdapter(contextFragment!!,it.pastOrders)
                    binding.recvPastOrders.layoutManager = LinearLayoutManager(contextFragment!!)



                }



            }





        })

        accountViewModel.userDetailResponseMutableLiveData.observe(this, Observer {

            if(it!=null){

                // SETTING ALL THE FIELDS IN THE VIEW FROM THE RESPONSE WE GOT FROM THE API.
                    //you can also add a null pointer method for the String just like in java
                binding.tvUserName.text = it.data!!.userName?:"USERNAME"
                binding.tvUserEmail.text = it.data!!.userEmail?:"USEREMAIL"

                var number: String? = it.data!!.number
                //THIS TELLS KOTLIN IF THE NUMBER IS EMPTY THEN WE CAN SET THE VALUE EMPTY TO THE EDIT TEXT
                //VERY COOL FEATURE OF KOTLIN?: THIS SETS THE DEFAULT VALUE >> WHILE ? THIS TELL KOTLIN THAT VALUE
                // CAN BE NULL
                number?:"EMPTY"

                binding.tvUserNumber.text = it.data!!.number


            }



        })



    }
}