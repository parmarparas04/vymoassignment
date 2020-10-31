package com.example.vymoassignment.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.assignment.base.components.BaseFragment

abstract class BaseFragmentMvvm<T : ViewDataBinding, V : BaseViewModelMvvm> : BaseFragment<T, V>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}