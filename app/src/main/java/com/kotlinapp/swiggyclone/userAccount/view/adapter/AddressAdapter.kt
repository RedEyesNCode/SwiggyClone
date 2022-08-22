package com.kotlinapp.swiggyclone.userAccount.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinapp.swiggyclone.databinding.AddressListLayoutBinding
import com.kotlinapp.swiggyclone.userAccount.model.AddressTables

class AddressAdapter(var context:Context ,var addressList:ArrayList<AddressTables>, var onClicked: AddressAdapter.onClickedAddress) :RecyclerView.Adapter<AddressAdapter.myViewHolder>() {
    var selectitem = -1

    lateinit var binding:AddressListLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        binding = AddressListLayoutBinding.inflate(LayoutInflater.from(context))
        return myViewHolder(binding)

    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        with(holder){
            with(binding){
                var addressTables = addressList.get(position)
                binding.tvAddress.text = addressTables.address +" "+ addressTables.apartMent+ " "+addressTables.city+" " +addressTables.postalCode
                binding.tvUserName.text = addressTables.firstName+" "+addressTables.lastName
                binding.tvUserNumber.text = addressTables.phoneNumber



            }


        }

        binding.btnEditAddress.setOnClickListener {
            onClicked.onEditAddress(addressList.get(position))
        }

        binding.rbnSelectRadio.setOnClickListener(View.OnClickListener {
            selectitem = position
            notifyDataSetChanged()
        })
        if (selectitem == position) {
            binding.rbnSelectRadio.setChecked(true)
        } else {
            binding.rbnSelectRadio.setChecked(false)
        }

    }

    override fun getItemCount(): Int {

        return addressList.size
    }

    interface onClickedAddress{
        fun onEditAddress(addressTables: AddressTables)
    }

    class myViewHolder(var addressListLayoutBinding: AddressListLayoutBinding):RecyclerView.ViewHolder(addressListLayoutBinding.root)
}