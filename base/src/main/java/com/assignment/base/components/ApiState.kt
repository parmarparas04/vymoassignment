package com.assignment.base.components

import com.assignment.base.MSG_SOMETHING_ERROR


data class ApiState(var state: State, var msg:String= MSG_SOMETHING_ERROR, var localMsg:String= MSG_SOMETHING_ERROR)