package com.example.testapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseViewModelFragment<VM : BaseViewModel, VB : ViewBinding>(private val inflate: Inflate<VB>) :
    Fragment() {

    abstract val viewModel: VM
    var binding: VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = inflate.invoke(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.inProgress.observe(viewLifecycleOwner) { isInProgress ->
            // If activity implements OnProgressListener - it handles progress by itself.
            if (activity is OnProgressListener) {
                (activity as OnProgressListener).showProgress(isInProgress)
            }
        }

        viewModel.showError.observe(viewLifecycleOwner, EventObserver{
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

}