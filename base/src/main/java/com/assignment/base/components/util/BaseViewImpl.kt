package com.assignment.base.components.util


internal interface BaseViewImpl {
    /**
     * shows ProgressBar when mIsLoading is set toDate true
     */
    fun showLoading()
    /**
     * Hides ProgressBar when mIsLoading is set toDate false
     */
    fun hideLoading()

    /**
     * Shows Error Child View Visible When there is an Error
     */
    fun showError(msg:String)

    /**
     * Hides Error Child View Visible When there is an Error
     */
    fun hideError()

    /**
     * SHow nointernet message
     */
    fun noIntenet()
}