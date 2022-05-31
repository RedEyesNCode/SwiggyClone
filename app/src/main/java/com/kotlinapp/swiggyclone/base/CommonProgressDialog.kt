package com.kotlinapp.swiggyclone.base

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.kotlinapp.swiggyclone.R

class CommonProgressDialog(context: Context) : Dialog(context) {

    var dialog = ProgressDialog(context)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.custom_progress_dialog)
        dialog.create()
    }

    fun showDialog(){
        dialog.show()

    }
    fun dissmissDialog(){
        dialog.dismiss()
    }


}