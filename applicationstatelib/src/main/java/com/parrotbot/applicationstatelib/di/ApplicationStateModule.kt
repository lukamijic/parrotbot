package com.parrotbot.applicationstatelib.di

import com.parrotbot.applicationstatelib.source.ApplicationStateSource
import com.parrotbot.applicationstatelib.source.ApplicationStateSourceImpl
import com.parrotbot.applicationstatelib.usecase.GetApplicationState
import org.koin.core.module.Module
import org.koin.dsl.module

fun applicationStateModule() : Module = module {

    single<ApplicationStateSource> { ApplicationStateSourceImpl() }

    single { GetApplicationState(get()) }
}
