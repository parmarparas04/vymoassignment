package com.assignment.base.components.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle

interface ViewNavigation {
    fun startActivity(intent: Intent?)
    fun startActivityForResult(intent: Intent?, requestCode: Int)
    fun <T> startActivityForResult(cls: Class<T>,requestCode: Int, extras: Bundle?=null)
    fun <T> openActivity(cls: Class<T>, extras: Bundle?=null)
    fun <T> openActivitynFinish(cls: Class<T>, extras: Bundle?=null)
    fun closeActivity()
    fun closeActivityWithResult(resultCode: Int)

    fun <T> openActivitynClearTask(cls: Class<T>, extras: Bundle?=null)

    fun openDialog(title:String="",msg:String="",type: String="")

}