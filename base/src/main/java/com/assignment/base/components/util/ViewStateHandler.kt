package com.assignment.base.components.util

import com.assignment.base.components.ApiState
import com.assignment.base.components.State


internal object ViewStateHandler {
    fun handleState(baseViewImpl: BaseViewImpl, newState:ApiState){
        when (newState.state) {
            State.ERROR -> {
                baseViewImpl.apply {
                    hideLoading()
                    newState.msg.let {
                        showError(it)
                    }

                }
            }
            State.LOADING -> {
                baseViewImpl.apply {
                    hideError()
                    showLoading()
                }
            }
            State.IDLE -> {
                baseViewImpl.apply {
                    hideLoading()
                    hideError()
                }
            }
            State.OFFLINE -> {
                baseViewImpl.apply {
                    hideLoading()
                    hideError()
                    noIntenet()
                }
            }
        }
    }
}