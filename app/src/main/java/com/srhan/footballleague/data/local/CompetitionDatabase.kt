package com.srhan.footballleague.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.srhan.footballleague.data.local.entities.CompetitionEntity

@Database(entities = [CompetitionEntity::class], version = 1)
abstract class CompetitionDatabase : RoomDatabase() {
    abstract fun competitionDao(): CompetitionDao
}