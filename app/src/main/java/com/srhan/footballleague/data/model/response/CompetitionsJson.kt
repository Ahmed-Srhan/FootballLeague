package com.srhan.footballleague.data.model.response

data class CompetitionsJson(
    val competitions: List<Competition>,
    val count: Int,
    val filters: Filters
)