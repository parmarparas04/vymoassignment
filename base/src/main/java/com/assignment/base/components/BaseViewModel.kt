package com.assignment.base.components

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.assignment.base.components.util.ApiLoadingInteraction
import com.assignment.base.components.util.LiveMessageEvent
import com.assignment.base.components.util.ViewMessage
import com.assignment.base.components.util.ViewNavigation
import com.assignment.base.data.repo.RepoManager

abstract class BaseViewModel(
    private val repoManager: RepoManager
) : ViewModel(), ApiLoadingInteraction {
    /**
     * viewState Mutable Live Data which emit the state of View from ViewModel
     */
    val mViewState = MutableLiveData<ApiState>()
    val navigationEvent = LiveMessageEvent<ViewNavigation>()
    val messagesEvent = LiveMessageEvent<ViewMessage>()
    val mErrorState = MutableLiveData<String>()


    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     * <p>
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    override fun onCleared() {
        super.onCleared()
    }

    /**
     * Returns Repo Manager
     */
    protected fun getRepoManager(): RepoManager {
        return repoManager
    }

    /**
     * set View State from ViewModel
     */
    protected fun setViewState(newState: ApiState) {
        mViewState.value = newState
    }

    /**
     * add View State Change Listener to viewState and start observing by the host
     */
    fun addViewStateChangeListener(lifecycleOwner: LifecycleOwner, observer: Observer<ApiState>) {
        mViewState.observe(lifecycleOwner, observer)
    }



    override fun isLoadingState(newState: ApiState){
        setViewState(newState)
    }

//    override fun apiErrorMessage(error: String) {
//        mErrorState.value = error
//    }
}