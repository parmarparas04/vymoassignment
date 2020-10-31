package com.example.vymoassignment.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import com.assignment.base.components.BaseActivity


abstract class BaseActivityMvvm<T : ViewDataBinding, V : BaseViewModelMvvm> : BaseActivity<T, V>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    protected fun setActionBar(toolbar: Toolbar, title:String?=null, isBlackButtonNotSet:Boolean = true){
        setSupportActionBar(toolbar)
        if(isBlackButtonNotSet)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if(!title.isNullOrEmpty())
            supportActionBar?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item.let {
            return when (it?.itemId) {
                android.R.id.home -> {
                    onBackPressed()
                    true
                }
                else -> return false
            }
        }
    }

}
