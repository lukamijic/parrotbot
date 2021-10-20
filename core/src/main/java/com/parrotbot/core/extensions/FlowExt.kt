package com.parrotbot.core.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

/** Shares the chain and replays the latest emission to new subscribers */
fun <T> Flow<T>.shareReplayLatest(sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(1000L)) =
    shareIn(CoroutineScope(Dispatchers.Default), sharingStarted, replay = 1)

