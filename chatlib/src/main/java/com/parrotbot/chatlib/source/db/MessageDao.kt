package com.parrotbot.chatlib.source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.parrotbot.chatlib.model.db.DbMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Query("SELECT * FROM dbmessage ORDER BY timestamp")
    fun messages() :  Flow<List<DbMessage>>

    @Insert
    suspend fun send(dbMessage: DbMessage)
}
