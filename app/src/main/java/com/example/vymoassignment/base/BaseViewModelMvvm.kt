package com.example.vymoassignment.base

import com.assignment.base.components.ApiState
import com.assignment.base.components.BaseViewModel
import com.assignment.base.components.State
import com.assignment.base.data.model.ApiResponse
import com.assignment.base.data.repo.RepoManager
import com.assignment.base.data.utils.NoInternetException
import com.example.vymoassignment.api.NetworkApiInterface

open class BaseViewModelMvvm(repoManager: RepoManager) :
        BaseViewModel(repoManager) {

    protected val networkApiInterface by lazy {
        getRepoManager().getApi("https://api.github.com", NetworkApiInterface::class.java)
    }

    protected fun <T : Any> handleError(output: ApiResponse<T>, apiState: ApiState) {
        setViewState(apiState)
    }

    protected fun  handleError(output:Throwable,apiState: ApiState) {
        when (output) {
            is NoInternetException -> {
                apiState.state = State.OFFLINE
                apiState.msg = "No internet, Please try again"
                setViewState(apiState)
            }
            else -> setViewState(apiState)
        }
    }



}