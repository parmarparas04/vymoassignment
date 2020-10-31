package com.example.vymoassignment.screen.prlist

import androidx.lifecycle.viewModelScope
import com.assignment.base.components.ApiState
import com.assignment.base.components.State
import com.assignment.base.data.repo.RepoManager
import com.example.vymoassignment.R
import com.example.vymoassignment.base.BaseViewModelMvvm
import com.example.vymoassignment.screen.prlist.adapter.PrListAdapter
import com.example.vymoassignment.screen.prlist.models.PrResponseItem
import kotlinx.coroutines.launch


class PrListViewModel(repoManager: RepoManager) : BaseViewModelMvvm(repoManager) {
    private val errorDetailRepository by lazy {
        PrListRepository(networkApiInterface,this)
    }

    private var adapter: PrListAdapter? = null

    init {
        getPullRequest()
        adapter = PrListAdapter(R.layout.e_pr_list)
    }

    private val TAG = "PrListViewModel"


    /**
     * Method responsible for handling api to get Error task
     */

    private fun getPullRequest() {
        viewModelScope.launch {
            runCatching {
                errorDetailRepository.getPr("https://api.github.com/repos/parmarparas04/assignment/pulls");
            }.onSuccess {response->
                if(response.code==-1){
                    response.data?.let {
                        setPrAdaptor(it);
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