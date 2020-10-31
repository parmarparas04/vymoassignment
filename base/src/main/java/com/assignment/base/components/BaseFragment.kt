package com.assignment.base.components

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.assignment.base.R
import com.assignment.base.components.util.BaseViewImpl
import com.assignment.base.components.util.ViewMessage
import com.assignment.base.components.util.ViewNavigation
import com.assignment.base.components.util.ViewStateHandler
import com.assignment.base.dialog.CustomProgressDialog
import org.koin.android.ext.android.inject



abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() , BaseViewImpl,ViewMessage, ViewNavigation {
    private var mRootView: View? = null
    var viewDataBinding: T? = null

    private var mProgressDialog: CustomProgressDialog? = null

    private var mViewModel: V? = null
    private val viewModelFactory:BaseViewModelFactory by inject()


    abstract val bindingVariable: Int

    var isNetworkConnected: Boolean = true

    abstract val layoutId: Int


    abstract val viewModelClass: Class<V>

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        this.mViewModel = if (mViewModel == null) ViewModelProvider(
                this,
                viewModelFactory
        ).get(viewModelClass) else mViewModel
        setHasOptionsMenu(false)
        getViewModel()?.messagesEvent?.setEventReceiver(this, this)
        getViewModel()?.navigationEvent?.setEventReceiver(this, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = viewDataBinding!!.root
        return mRootView
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.setVariable(bindingVariable, mViewModel)
        viewDataBinding?.lifecycleOwner = this
        viewDataBinding?.executePendingBindings()
        performDataBinding()
    }

    private fun performDataBinding() {
        mViewModel = if (mViewModel == null) {
            ViewModelProvider(this,viewModelFactory).get(viewModelClass)
        }
        else mViewModel
        viewDataBinding?.setVariable(bindingVariable, mViewModel)
        viewDataBinding?.executePendingBindings()
        mViewModel?.mViewState?.observe(viewLifecycleOwner,
                Observer<ApiState> { t ->
                    onViewStateChanged(t)
                })

    }
    private fun onViewStateChanged(newState: ApiState) {
        ViewStateHandler.handleState(this, newState)
    }

    private fun performDependencyInjection() {
        //AndroidSupportInjection.inject(this);
    }

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String?)
    }

    override fun noIntenet() {
        showToast(getString(R.string.no_internet_msg))
    }


    /**
     * shows A Long Toast Showing the msg on It
     *
     * @param msg the Message
     */
    private fun showToast(msg: String) {
        Toast.makeText(activity,msg, Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog?.cancel()
        }
    }


    override fun showLoading() {
        hideLoading()
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }

        if(isActivityInForeground()) {
            mProgressDialog = CustomProgressDialog(activity!!);
            mProgressDialog?.show()
        }
//        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

    override fun showError(msg: String) {
        showToast(msg)
    }

    override fun hideError() {

    }

    fun getViewModel() = mViewModel

    override fun <T> openActivity(cls: Class<T>, extras: Bundle?) {
        Intent(activity, cls).apply {
            if (extras != null)
                putExtras(extras)
            startActivity(this)
        }
    }
    override fun <T> openActivitynClearTask(cls: Class<T>, extras: Bundle?) {
        Intent(activity, cls).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            if (extras != null)
                putExtras(extras)
            startActivity(this)
        }
    }
    override fun closeActivity() {
        activity?.finish()
    }
    override fun closeActivityWithResult(resultCode: Int) {
        activity?.setResult(resultCode)
        activity?.finish()
    }


    override fun openDialog(title: String, msg: String, type: String) =Unit
    override fun <T> openActivitynFinish(cls: Class<T>, extras: Bundle?) {
        Intent(activity, cls).apply {
            if (extras != null)
                putExtras(extras)
            startActivity(this)
        }
        activity?.finish()
    }
    override fun <T> startActivityForResult(cls: Class<T>, requestCode: Int, extras: Bundle?) {
        Intent(activity, cls).apply {
            if (extras != null)
                putExtras(extras)
            startActivityForResult(this,requestCode)
        }
    }
    override fun showMessage(msg: String) {
        showToast(msg)
    }
    //to check activity in background or not
    protected fun isActivityInForeground():Boolean = (activity != null && isAdded)

}