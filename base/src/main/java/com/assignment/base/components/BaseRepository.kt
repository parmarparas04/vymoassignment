package com.assignment.base.components

import com.assignment.base.MSG_SOMETHING_ERROR
import com.assignment.base.components.util.ApiLoadingInteraction
import com.assignment.base.components.util.safeLet
import com.assignment.base.data.model.ApiResponse
import org.json.JSONObject
import retrofit2.Response


open class BaseRepository (apiLoadingInteraction: ApiLoadingInteraction){
    var loadingInteraction: ApiLoadingInteraction? = null
    init {
        loadingInteraction = apiLoadingInteraction;
    }
    suspend fun<T : Any> apiOutputHit(call: suspend()-> Response<T>,isSilent:Boolean=false,isApiSafeHandle :Boolean=true) : ApiResponse<T> {
        val apiState = ApiState(State.LOADING,"","")
        if(!isSilent) {
            apiState.state = State.LOADING
            loadingInteraction?.isLoadingState(apiState)
        }
        val response = call.invoke()
        val output = ApiResponse<T>()
        output.isSafeCheckApi = isApiSafeHandle
        if (response.isSuccessful) {
            if(!isSilent) {
                apiState.state = State.IDLE
                loadingInteraction?.isLoadingState(apiState)
            }
            response.body()?.let {
                output.data =it
            }
        }
        else{
            output.code = response.code()
            output.message = response.message()
            response.errorBody()?.let {
                try {
                    val jObjError = JSONObject(it.string())
                    output.message = jObjError.getString("message")
                    output.localizedMessage = jObjError.getString("message")
                } catch (e: Exception) {
                    output.message = MSG_SOMETHING_ERROR
                    output.localizedMessage = MSG_SOMETHING_ERROR
                }
            }?:run {
                output.message = MSG_SOMETHING_ERROR
                output.localizedMessage = MSG_SOMETHING_ERROR
            }
            if(isApiSafeHandle) {
                apiState.state = State.ERROR
                safeLet(output.message,output.localizedMessage){msg,localMsg->
                    apiState.msg = msg
                    apiState.msg = output.message!!
                }
                loadingInteraction?.isLoadingState(apiState)
            }
        }
        return  output
    }
}