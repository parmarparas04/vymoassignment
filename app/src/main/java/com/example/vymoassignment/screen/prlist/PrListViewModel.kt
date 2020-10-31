package com.example.vymoassignment.screen.prlist

import androidx.lifecycle.viewModelScope
import com.assignment.base.components.ApiState
import com.assignment.base.components.State
import com.assignment.base.data.repo.RepoManager
import com.example.vymoassignment.R
import com.example.vymoassignment.base.BaseViewModelMvvm
import com.example.vymoassignment.screen.prlist.adapter.PrListAdapter
import com.example.vymoassignment.screen.prlist.models.PrResponseItem
import com.example.vymoassignment.utils.MSG_NO_PR
import kotlinx.coroutines.launch


class PrListViewModel(repoManager: RepoManager) : BaseViewModelMvvm(repoManager) {
    private val errorDetailRepository by lazy {
        PrListRepository(networkApiInterface,this)
    }

    private var adapter: PrListAdapter? = null

    init {
        adapter = PrListAdapter(R.layout.e_pr_list)
    }

    private val TAG = "PrListViewModel"


    /**
     * Method responsible for handling api to get Error task
     */

    fun getPullRequest(url:String) {
        viewModelScope.launch {
            runCatching {
                errorDetailRepository.getPr(url);
            }.onSuccess {response->
                if(response.code==-1){
                    response.data?.let {
                        if(!it.isNullOrEmpty())
                            setPrAdaptor(it)
                        else{
                            messagesEvent.sendEvent { showMessage(MSG_NO_PR) }
                        }
                    }
                }
            }.onFailure {
                handleError(it, ApiState(State.ERROR))
            }
        }
    }

    private fun setPrAdaptor(prList: List<PrResponseItem>) {
        adapter?.setPRList(prList)
        adapter?.notifyDataSetChanged()
    }
    fun getAdapter(): PrListAdapter? {
        return adapter
    }


}