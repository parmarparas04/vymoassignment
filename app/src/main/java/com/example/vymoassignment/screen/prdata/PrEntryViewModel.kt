package com.example.vymoassignment.screen.prdata
import androidx.core.os.bundleOf
import com.assignment.base.data.repo.RepoManager
import com.example.vymoassignment.base.BaseViewModelMvvm
import com.example.vymoassignment.screen.prdata.model.PrEntryForm
import com.example.vymoassignment.screen.prlist.PrListActivity
import com.example.vymoassignment.utils.PARAM_INTENT_NAME
import com.example.vymoassignment.utils.PARAM_INTENT_REPO

class PrEntryViewModel(repoManager: RepoManager) : BaseViewModelMvvm(repoManager) {
    /**
     * for handling form data
     */
    var prEntryForm = PrEntryForm()


    /**
     * button click for submit
     * */
    fun submitClicked() {
        if (prEntryForm.validateFields()) {
            navigationEvent.sendEvent {
                openActivity(
                    PrListActivity::class.java, bundleOf(
                        PARAM_INTENT_NAME to prEntryForm.name,
                        PARAM_INTENT_REPO to prEntryForm.repo
                    )
                )
            }
        }
    }
}