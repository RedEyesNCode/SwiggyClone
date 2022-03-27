package com.kotlinapp.swiggyclone.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.kotlinapp.swiggyclone.R

class CommonProgressDialog(context: Context) : Dialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_progress_dialog)
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


}