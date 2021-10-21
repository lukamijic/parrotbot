package com.parrotbot.chatlib.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbMessage(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, //Insert methods treat 0 as not-set while inserting the item
    @ColumnInfo(name = "message")
    val message: String,
    @ColumnInfo(name = "timestamp")
    val timeStamp: Long,
    @ColumnInfo(name = "sender")
    val sender: DbSender
)
