package com.srhan.footballleague.data.remote

import com.srhan.footballleague.data.model.response.CompetitionsJson
import com.srhan.footballleague.utils.Constant.ALL_COMPETITIONS
import retrofit2.Response
import retrofit2.http.GET

interface CompetitionApiService {
    @GET(ALL_COMPETITIONS)
    suspend fun getAllCompetitionsFromNetwork(): Response<CompetitionsJson>

}