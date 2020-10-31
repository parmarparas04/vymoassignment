package com.assignment.base.components.util

import com.assignment.base.components.ApiState

interface ApiLoadingInteraction{
    fun isLoadingState(newState: ApiState)
}