package com.parrotbot.coreui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.reflect.KClass

abstract class BaseViewModel<ViewState: Any> : ViewModel() {

    protected val bgScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private val viewStateMap: MutableMap<KClass<ViewState>, SharedFlow<ViewState>> = mutableMapOf()

    val viewStates: Collection<SharedFlow<ViewState>> = viewStateMap.values

    protected inline fun <reified T : ViewState> query(toFlow: () -> Flow<T>) {
        storeViewState(
            T::class as KClass<ViewState>,
            toFlow().shareIn(bgScope, SharingStarted.Eagerly, 1)
        )
    }

    protected inline fun <reified T : ViewState> query(
        viewStateInitialValue: T,
        toFlow: () -> Flow<T>
    ) {
        storeViewState(
            T::class as KClass<ViewState>,
            toFlow().stateIn(bgScope, SharingStarted.Eagerly, viewStateInitialValue)
        )
    }

    fun runCommand(action: suspend () -> Unit) = bgScope.launch { action() }

    /**
     * Stores [Flow] of [ViewState].
     * If map already has Flow<ViewState> stored throws an exception.
     * Reason is we want to have single [Flow] providing given [ViewState].
     * This makes debugging easier since developer will immediately find faulty query.
     */
    protected fun storeViewState(key: KClass<ViewState>, sharedFlow: SharedFlow<ViewState>) {
        if (viewStateMap.containsKey(key)) throw IllegalArgumentException("StateFlow for ${key.simpleName} already exists")
        viewStateMap[key] = sharedFlow
    }

    override fun onCleared() {
        bgScope.cancel()
        super.onCleared()
    }
}
