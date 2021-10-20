package com.parrotbot.coreui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseFragment<ViewState : Any, Binding : ViewBinding>(private val bindingInflater: (LayoutInflater) -> Binding) : Fragment() {

    private var _binding: Binding? = null
    protected val binding: Binding
        get() = _binding!!

    abstract val model: BaseViewModel<ViewState>

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater(layoutInflater)
        return binding.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initializeView(savedInstanceState)
        subscribeToViewState()
    }

    protected open fun Binding.initializeView(savedInstanceState: Bundle?) { }

    open fun Binding.render(viewState: ViewState): Unit { }

    private fun subscribeToViewState() {
        model.viewStates.forEach { sharedFlow ->
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    sharedFlow.collect { viewState -> binding.render(viewState) }
                }
            }
        }
    }
}
