package com.kotlinapp.swiggyclone.utils

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import com.kotlinapp.swiggyclone.R

class CommonInteractiveDialog(var context: Context) {
    private  lateinit var dialog: Dialog
    private lateinit var onClickDialog: onClick


    fun CommonDialogBox(context: Context, onClick: onClick) {
        this.onClickDialog = onClick
        dialog = Dialog(context, R.style.RoundedCornersDialog)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.common_dialog_interactive)
        val lp = WindowManager.LayoutParams()
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER
    }


    fun showCommonDialog(setText: String?,alertTitle:String?) {
        dialog.show()
        val btnNo: LinearLayout
        val btnYes: LinearLayout
        val mainLayout: LinearLayout
        mainLayout = dialog.findViewById<LinearLayout>(R.id.mainLinearLayout)
        mainLayout.fitsSystemWindows = true
        val requiredText: TextView
        val tileText: TextView
        requiredText = dialog.findViewById<TextView>(R.id.tvSetText)
        requiredText.text = setText
        tileText = dialog.findViewById<TextView>(R.id.alertTitle)
        tileText.text = alertTitle
        btnNo = dialog.findViewById<LinearLayout>(R.id.btnNo)
        btnYes = dialog.findViewById<LinearLayout>(R.id.btnYes)
        btnNo.setOnClickListener { dialog.dismiss()
        onClickDialog.onNegative()}
        btnYes.setOnClickListener { dialog.dismiss()

        onClickDialog.onPositive()
        }
    }
    interface onClick{
        fun onPositive()
        fun onNegative()

    }
}