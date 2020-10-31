package com.assignment.base.components

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.assignment.base.R
import com.assignment.base.components.util.BaseViewImpl
import com.assignment.base.components.util.ViewMessage
import com.assignment.base.components.util.ViewNavigation
import com.assignment.base.components.util.ViewStateHandler
import com.assignment.base.data.model.ApiResponse
import com.assignment.base.dialog.CustomProgressDialog
import org.koin.android.ext.android.inject

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity(),
        BaseFragment.Callback, BaseViewImpl,ViewMessage, ViewNavigation{

    override fun showMessage(msg: String) {
        showToast(msg)
    }

    override fun closeActivity() {
        finish()
    }



    override fun openDialog(title: String, msg: String,type:String) {
    }


    /**
     * Open Activity as Given By Class With Extras and need toDate be implement
     *
     * @param T type of Activity Class We are about toDate be Open
     * @param cls Activity Class We are about toDate be Open
     * @param extras Optional extras Which will be moved as a Bundle.
     */
    override fun <T> openActivity(cls: Class<T>, extras: Bundle?) {
        Intent(this, cls).apply {
            if (extras != null)
                putExtras(extras)
            startActivity(this)
        }
    }

    override fun <T> startActivityForResult(cls: Class<T>, requestCode: Int, extras: Bundle?) {
        Intent(this, cls).apply {
            if (extras != null)
                putExtras(extras)
            startActivityForResult(this,requestCode)
        }
    }
    override fun closeActivityWithResult(resultCode: Int) {
        setResult(resultCode)
        finish()
    }
    override fun <T> openActivitynClearTask(cls: Class<T>, extras: Bundle?) {
        Intent(this, cls).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            if (extras != null)
                putExtras(extras)
            startActivity(this)
        }
    }

    override fun <T> openActivitynFinish(cls: Class<T>, extras: Bundle?) {
        Intent(this, cls).apply {
            if (extras != null)
                putExtras(extras)
            startActivity(this)
        }
        finish()
    }

    /**
     * shows A Long Toast Showing the msg on It
     *
     * @param msg the Message
     */
    fun showToast(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    private val viewModelFactory:BaseViewModelFactory by inject()

    private var mProgressDialog: CustomProgressDialog? = null
    protected var mViewDataBinding: T? = null
    var mViewModel: V? = null

    abstract val bindingVariable: Int

    abstract val layoutId: Int

    abstract val viewModelClass: Class<V>


    override fun onFragmentAttached() {

    }

    override fun showError(msg: String) {
        showToast(msg)
    }

    override fun hideError() {

    }

    fun getViewModel() = mViewModel


    override fun onFragmentDetached(tag: String?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
        getViewModel()?.messagesEvent?.setEventReceiver(this, this)
        getViewModel()?.navigationEvent?.setEventReceiver(this, this)
    }

    fun hasPermission(permission: String?): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || checkSelfPermission(permission!!) == PackageManager.PERMISSION_GRANTED
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog?.cancel()
        }
    }

    var isNetworkConnected: Boolean = true
    //get() = NetworkUtils.isNetworkConnected(applicationContext)

    fun openActivityOnTokenExpire() { // redirect to login

    }

    fun performDependencyInjection() {
        //TODO : inject
        // AndroidInjection.inject(this);
    }

    fun requestPermissionsSafely(
            permissions: Array<String?>?,
            requestCode: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions!!, requestCode)
        }
    }

    override fun showLoading() {
        hideLoading()
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
        mProgressDialog = CustomProgressDialog(this);
        mProgressDialog?.show()
//        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        mViewModel = if (mViewModel == null) {
            ViewModelProvider(this,viewModelFactory).get(viewModelClass)
        }
        else mViewModel
        mViewDataBinding?.setVariable(bindingVariable, mViewModel)
        mViewDataBinding?.executePendingBindings()
        mViewModel?.mViewState?.observe(this,
                Observer<ApiState> { t ->
                    onViewStateChanged(t)
                })
    }
    private fun onViewStateChanged(newState: ApiState) {
        ViewStateHandler.handleState(this, newState)
    }

    /**
     * Calls When Api Failed
     *
     * @param response Response
     * @param t Throwable
     */
    fun <T : Any> onApiFailed(response: ApiResponse<T>?) {
    }

    override fun noIntenet() {
        showToast(getString(R.string.no_internet_msg))
    }
}