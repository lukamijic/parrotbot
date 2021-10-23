package com.parrotbot.applicationstatelib.usecase

import com.parrotbot.applicationstatelib.source.ApplicationStateSource

class GetApplicationState(private val applicationStateSource: ApplicationStateSource) {

    suspend operator fun invoke() = applicationStateSource.applicationState()
}
