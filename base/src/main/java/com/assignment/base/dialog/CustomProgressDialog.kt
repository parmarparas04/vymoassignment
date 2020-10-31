package com.assignment.base.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import com.assignment.base.R


class CustomProgressDialog(context: Context) : Dialog(context, R.style.TransparentProgressDialog) {

    override fun show() {
        super.show()
    }
    override fun dismiss() {
        super.dismiss()
    }
    init {
        val wlmp = window?.attributes
        wlmp?.gravity = Gravity.CENTER
        window?.attributes = wlmp
        setTitle(null)
        setCancelable(false)
        setOnCancelListener(null)
        setContentView(R.layout.progress)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}