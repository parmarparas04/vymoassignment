package com.example.vymoassignment.screen.prlist

import com.assignment.base.components.BaseRepository
import com.assignment.base.components.util.ApiLoadingInteraction
import com.assignment.base.data.model.ApiResponse
import com.example.vymoassignment.api.NetworkApiInterface
import com.example.vymoassignment.screen.prlist.models.PrResponse

class PrListRepository(private val api : NetworkApiInterface, private val apiLoadingInteraction: ApiLoadingInteraction) : BaseRepository(apiLoadingInteraction) {
    /**
     * Responsible for get pr
     */
    suspend fun getPr(url:String): ApiResponse<PrResponse> {
        return apiOutputHit(
                call = {api.getPrList(url)}
        )
    }

}