package com.example.vymoassignment.api

import com.example.vymoassignment.screen.prlist.models.PrResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface NetworkApiInterface {
    @GET
    suspend fun getPrList(@Url url:String): Response<PrResponse>

}