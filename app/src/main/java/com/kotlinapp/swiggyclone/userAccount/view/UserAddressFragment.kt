package com.kotlinapp.swiggyclone.userAccount.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.kotlinapp.swiggyclone.base.BaseFragment
import com.kotlinapp.swiggyclone.databinding.FragmentUserAddressBinding
import com.kotlinapp.swiggyclone.sharedPreferences.AppSession
import com.kotlinapp.swiggyclone.sharedPreferences.Constant
import com.kotlinapp.swiggyclone.smoothieKotlin.repository.AppRepository
import com.kotlinapp.swiggyclone.smoothieKotlin.viewModel.LoginViewModelCoroutines
import com.kotlinapp.swiggyclone.smoothieKotlin.viewModelFactory.ViewModelProviderFactory
import com.kotlinapp.swiggyclone.userAccount.model.AddressInputBody
import com.kotlinapp.swiggyclone.userAccount.viewModel.AccountViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserAddressFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserAddressFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding:FragmentUserAddressBinding
    private lateinit var viewModel:AccountViewModel
    private var ADDRESS_INFO: AddressInputBody = AddressInputBody()

    private lateinit var contextFragment:Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contextFragment = context;
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
        initCoroutine()
        attachObservers()
    }
    fun initCoroutine() {
        val repository = AppRepository()
        var factory = ViewModelProviderFactory(requireActivity().application,repository)
        viewModel = ViewModelProvider(this,factory).get(AccountViewModel::class.java)
    }
    private fun attachObservers() {
        viewModel.commonStatusMessageResponse.observe((viewLifecycleOwner)) {
            if (it.status?.contains("success") == true){
                showToast(it.message.toString())
                requireFragmentManager().popBackStack()
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentUserAddressBinding.inflate(inflater,container,false)
        initClicks()



        return binding.root
    }

    private fun initClicks() {
        binding.commonTitleBar.uiTitle.setText("Your addresses")
        binding.commonTitleBar.backIcon.setOnClickListener { activity?.onBackPressed() }

        binding.btnSaveAddress.setOnClickListener {
            val firstName = binding.shipFirstName.text.toString()
            val lastName = binding.shipLastName.text.toString()
            val phoneNumber = binding.shipPhoneNumber.text.toString()
            val address = binding.shipAddress.text.toString()
            val postalCode = binding.shipPostalCode.text.toString()
            val city = binding.shipCityReal.text.toString()
            val state = binding.shipCity.text.toString()
            val country ="India"

            if(firstName.isEmpty()){
                binding.shipFirstName.setError("Please enter your first name")
            }else if(lastName.isEmpty()){
                binding.shipLastName.setError("Please enter your last name")
            }else if(phoneNumber.isEmpty()){
                binding.shipPhoneNumber.setError("Please enter your mobile number")

            }else if(address.isEmpty()){
                binding.shipAddress.setError("Please enter your address")
            }else if(postalCode.isEmpty()){
                binding.shipPostalCode.setError("Please enter your postal code.")
            }else if(city.isEmpty()){
                binding.shipCityReal.setError("Please enter your city.")
            }else if(state.isEmpty()){
                binding.shipCity.setError("Please enter your state.")
            }else {
                // Call the add address api.
                ADDRESS_INFO.firstName = firstName
                ADDRESS_INFO.lastName = lastName
                ADDRESS_INFO.phoneNumber = phoneNumber
                ADDRESS_INFO.address = address
                ADDRESS_INFO.postalCode = postalCode
                ADDRESS_INFO.city = city
                ADDRESS_INFO.apartMent = state
                ADDRESS_INFO.userId = AppSession(contextFragment).getValue(Constant().USER_ID,contextFragment)?.toInt()


                viewModel.saveUserAddress(getAccessToken(),ADDRESS_INFO)


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
         * @return A new instance of fragment UserAddressFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserAddressFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}