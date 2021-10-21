package com.parrotbot.chatlib.model.domain

data class Message(
    val id: Int,
    val message: String,
    val timestamp: Long,
    val sender: Sender
)

enum class Sender {
    USER, PARROT_BOT
}
