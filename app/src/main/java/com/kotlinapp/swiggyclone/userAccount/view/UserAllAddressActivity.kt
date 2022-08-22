package com.kotlinapp.swiggyclone.userAccount.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlinapp.swiggyclone.R
import com.kotlinapp.swiggyclone.base.BaseActivity
import com.kotlinapp.swiggyclone.cart.view.cartAddress.CartAddressFragment
import com.kotlinapp.swiggyclone.databinding.ActivityUserAllAddressBinding
import com.kotlinapp.swiggyclone.smoothieKotlin.repository.AppRepository
import com.kotlinapp.swiggyclone.smoothieKotlin.viewModelFactory.ViewModelProviderFactory
import com.kotlinapp.swiggyclone.userAccount.model.AddressTables
import com.kotlinapp.swiggyclone.userAccount.view.adapter.AddressAdapter
import com.kotlinapp.swiggyclone.userAccount.viewModel.AccountViewModel
import com.kotlinapp.swiggyclone.utils.FragmentUtils

class UserAllAddressActivity : BaseActivity() , AddressAdapter.onClickedAddress {

    lateinit var binding:ActivityUserAllAddressBinding
    private lateinit var viewModel: AccountViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserAllAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicks()
        initCoroutines()
    }
    private fun initCoroutines() {
        val repository = AppRepository()
        var factory = ViewModelProviderFactory(this.application,repository)
        viewModel = ViewModelProvider(this,factory).get(AccountViewModel::class.java)
        viewModel.getUserAddress(getAccessToken(),getUserId())
        viewModel.addressResponseMutableLiveData.observe((this)) {
            binding.recvAddress.adapter = AddressAdapter(this,it.addressTables,this)
            binding.recvAddress.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false)
        }
    }

    private fun initClicks() {
        binding.title.uiTitle.text = "Your address list"
        binding.title.backIcon.setOnClickListener {
            onBackPressed()
        }

        binding.btnAddNewAddress.setOnClickListener {
            // Load the edit user address fragment

        }
    }

    override fun onEditAddress(addressTables: AddressTables) {
        // Navigation to edit address fragment, or activity
        FragmentUtils().addFragmentBackStack(supportFragmentManager,R.id.addressContainer,UserAddressFragment(),
            CartAddressFragment::class.java.simpleName,true)
    }
}