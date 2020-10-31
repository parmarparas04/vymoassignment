package com.example.vymoassignment.screen.prlist

import android.os.Bundle
import com.example.vymoassignment.R
import com.example.vymoassignment.BR
import com.example.vymoassignment.base.BaseActivityMvvm
import com.example.vymoassignment.databinding.ActivityPrListBinding
import com.example.vymoassignment.utils.PARAM_INTENT_NAME
import com.example.vymoassignment.utils.PARAM_INTENT_REPO

class PrListActivity : BaseActivityMvvm<ActivityPrListBinding, PrListViewModel>()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(intent.hasExtra(PARAM_INTENT_NAME)){
            getViewModel()?.getPullRequest(String.format(getString(R.string.url) , intent.getStringExtra(PARAM_INTENT_NAME),intent.getStringExtra(
                PARAM_INTENT_REPO)))
        }
    }

    override val bindingVariable: Int
        get() = BR.viewModel //To change initializer of created properties use File | Settings | File Templates.
//    get() = 1

    override val layoutId: Int
        get() = R.layout.activity_pr_list //To change initializer of created properties use File | Settings | File Templates.

    override val viewModelClass = PrListViewModel::class.java

}
