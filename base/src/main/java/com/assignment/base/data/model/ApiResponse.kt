package com.assignment.base.data.model

class ApiResponse< T : Any>{
    var data: T? = null
    var message: String?=null
    var localizedMessage: String?=null
    var code:Int =-1
    var isSafeCheckApi:Boolean =true
}