package com.srhan.footballleague.data.model.response

data class Winner(
    val address: String,
    val clubColors: String,
    val crest: String,
    val founded: Int,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val shortName: String,
    val tla: String,
    val venue: String,
    val website: String
)