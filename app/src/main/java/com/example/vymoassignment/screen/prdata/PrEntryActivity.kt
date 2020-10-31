package com.example.vymoassignment.screen.prdata

import android.os.Bundle
import com.example.vymoassignment.BR
import com.example.vymoassignment.R
import com.example.vymoassignment.base.BaseActivityMvvm
import com.example.vymoassignment.databinding.ActivityPrEntryBinding
import com.example.vymoassignment.screen.prlist.PrListViewModel
import kotlinx.android.synthetic.main.activity_pr_entry.*

class PrEntryActivity :  BaseActivityMvvm<ActivityPrEntryBinding, PrEntryViewModel>()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edVwName.requestFocus()
    }

    override val bindingVariable: Int
    get() = BR.viewModel //To change initializer of created properties use File | Settings | File Templates.
//    get() = 1

    override val layoutId: Int
    get() = R.layout.activity_pr_entry //To change initializer of created properties use File | Settings | File Templates.

    override val viewModelClass = PrEntryViewModel::class.java

}
