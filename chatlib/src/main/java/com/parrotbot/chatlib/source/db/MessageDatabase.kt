package com.parrotbot.chatlib.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.parrotbot.chatlib.model.db.DbMessage
import com.parrotbot.chatlib.model.db.DbSender

@Database(entities = [DbMessage::class], version = 1)
@TypeConverters(DbSender.Converter::class)
abstract class MessageDatabase : RoomDatabase() {

    companion object {
        const val NAME = "database_name"
    }

    abstract fun messageDao(): MessageDao
}
