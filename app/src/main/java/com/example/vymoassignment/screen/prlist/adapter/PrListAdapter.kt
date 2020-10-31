package com.example.vymoassignment.screen.prlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.vymoassignment.BR
import com.example.vymoassignment.screen.prlist.adapter.PrListAdapter.GenericViewHolder
import com.example.vymoassignment.screen.prlist.models.PrResponseItem

class PrListAdapter(@param:LayoutRes private val layoutId: Int) :
    RecyclerView.Adapter<GenericViewHolder>() {
    private var prList: List<PrResponseItem> = ArrayList()
    private fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }

    override fun getItemCount(): Int {
        return prList.size
    }
    private fun getObjForPosition(position: Int): PrResponseItem {
        return prList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return GenericViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val obj = getObjForPosition(position)
        holder.bind(obj)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun setPRList(prs: List<PrResponseItem>) {
        prList = prs
    }

    inner class GenericViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: PrResponseItem) {
            binding.setVariable(BR.obj, obj)
            binding.executePendingBindings()
        }

    }

}