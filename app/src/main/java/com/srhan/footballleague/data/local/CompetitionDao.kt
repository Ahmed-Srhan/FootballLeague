package com.srhan.footballleague.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.srhan.footballleague.data.local.entities.CompetitionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CompetitionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheCompetition(competition: List<CompetitionEntity>)

    @Query("SELECT * FROM competition")
    fun getAllCompetitionsFromLocal(): Flow<List<CompetitionEntity>>

    @Query("DELETE FROM competition")
    suspend fun deleteAllCompetitions(): Int
}