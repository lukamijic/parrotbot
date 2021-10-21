package com.parrotbot.chatlib.model.db

import androidx.room.TypeConverter

enum class DbSender {
    USER,
    PARROT_BOT;

    class Converter {

        @TypeConverter
        fun toDbSender(value: String) = enumValueOf<DbSender>(value)

        @TypeConverter
        fun fromDbSender(value: DbSender) = value.name
    }
}
