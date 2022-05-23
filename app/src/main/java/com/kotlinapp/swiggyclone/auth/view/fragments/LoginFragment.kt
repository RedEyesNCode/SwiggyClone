package com.kotlinapp.swiggyclone.auth.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.auth.model.LoginInputBody
import com.kotlinapp.swiggyclone.auth.view.LoginActivity
import com.kotlinapp.swiggyclone.auth.viewModel.LoginViewModel
import com.kotlinapp.swiggyclone.databinding.FragmentLoginBinding
import com.kotlinapp.swiggyclone.homeScreen.view.HomeActivity
import com.kotlinapp.swiggyclone.sharedPreferences.AppSession
import com.kotlinapp.swiggyclone.sharedPreferences.Constant
import com.kotlinapp.swiggyclone.smoothieKotlin.repository.AppRepository
import com.kotlinapp.swiggyclone.smoothieKotlin.viewModel.LoginViewModelCoroutines
import com.kotlinapp.swiggyclone.smoothieKotlin.viewModelFactory.ViewModelProviderFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding:FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private  lateinit var loginFragmentContext:Context
    private lateinit var loginViewModelCoroutines:LoginViewModelCoroutines

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.loginFragmentContext = context
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
    ): View {
        // INTIALZE THE BINDING IN THE FRAGMENT
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        viewModel =ViewModelProvider(this).get(LoginViewModel::class.java)

        /*viewModel = this!!.run {
         ViewModelProvider(this).get(LoginViewModel::class.java)
     }*/
        initCoroutine()
        attachObserversofCouroutines()
        initClicks()

        return  binding.root



    }
    fun initCoroutine() {
        val repository = AppRepository()
        var factory = ViewModelProviderFactory(requireActivity().application,repository)
        loginViewModelCoroutines = ViewModelProvider(this,factory).get(LoginViewModelCoroutines::class.java)
    }
    fun initClicks() {
        binding.btnLogin.setOnClickListener {
            var email = binding.edtloginEmail.text.toString()
            var password = binding.edtLoginpassword.text.toString()
            if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(email)){
                var loginInputBody = LoginInputBody(email,password)
//                viewModel.loginApiCall(loginFragmentContext,loginInputBody)
                loginViewModelCoroutines.loginUser(loginInputBody)
            }else{
                Toast.makeText(loginFragmentContext,"Please fill in the valid details", Toast.LENGTH_SHORT).show()

            }

        }


    }

    fun attachObserversofCouroutines(){
        loginViewModelCoroutines.loginResponse.observe(this, Observer { event ->

            if (event.peekContent().data?.code == 200 && event.peekContent().data?.status!!.contains("success")) {

                //NOT USING THE CONSTANT STRING CLASS BECAUSE IT CAN'T STORE THE SPECIFIC VALUES IN THE STRING
                AppSession(loginFragmentContext).clearAll()


                AppSession(loginFragmentContext).setValue(Constant().USER_ID,event.peekContent().data?.data!!.id.toString(),loginFragmentContext)
                AppSession(loginFragmentContext).setValue(Constant().ACCESS_TOKEN,event.peekContent().data?.Token,loginFragmentContext)

                var stringAccessToken  = AppSession(loginFragmentContext).getValue(Constant().ACCESS_TOKEN,loginFragmentContext)
                var userID  = AppSession(loginFragmentContext).getValue(Constant().USER_ID,loginFragmentContext)

                if(stringAccessToken==null){
                    Toast.makeText(loginFragmentContext,"USER SESSION NOT CREATED !", Toast.LENGTH_SHORT).show()

                }else{
                    startActivity(Intent(loginFragmentContext, HomeActivity::class.java))


                }
            }else{

                Toast.makeText(loginFragmentContext,"Login Failed", Toast.LENGTH_SHORT).show()
            }
        })


    }
    fun attachObservers(){
        viewModel.loginDataClassLiveData?.observe(this, Observer {


            if (it.code == 200 && it.status!!.contains("success")) {

                //NOT USING THE CONSTANT STRING CLASS BECAUSE IT CAN'T STORE THE SPECIFIC VALUES IN THE STRING
                AppSession(loginFragmentContext).clearAll()


                AppSession(loginFragmentContext).setValue(Constant().USER_ID,it.data!!.id.toString(),loginFragmentContext)
                AppSession(loginFragmentContext).setValue(Constant().ACCESS_TOKEN,it.Token,loginFragmentContext)

                var stringAccessToken  = AppSession(loginFragmentContext).getValue(Constant().ACCESS_TOKEN,loginFragmentContext)
                var userID  = AppSession(loginFragmentContext).getValue(Constant().USER_ID,loginFragmentContext)

                if(stringAccessToken==null){
                    Toast.makeText(loginFragmentContext,"USER SESSION NOT CREATED !", Toast.LENGTH_SHORT).show()

                }else{
                    startActivity(Intent(loginFragmentContext, HomeActivity::class.java))


                }
            }else{

                Toast.makeText(loginFragmentContext,"Login Failed", Toast.LENGTH_SHORT).show()
            }
        })













    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}