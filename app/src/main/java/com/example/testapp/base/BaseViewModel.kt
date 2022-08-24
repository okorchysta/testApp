package com.example.testapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.base_core.Result
import retrofit2.Response

abstract class BaseViewModel : ViewModel() {

    protected val inProgressMutable: MutableLiveData<Boolean> = MutableLiveData()
    val inProgress: LiveData<Boolean> = inProgressMutable

    private val showErrorMutable: MutableLiveData<Event<String>> = MutableLiveData()
    val showError: LiveData<Event<String>> = showErrorMutable


    fun showError(error: String) {
        inProgressMutable.postValue(false)
        showErrorMutable.postEvent(error)
    }

    fun <T: Any> handleResponse(response: Result<T>, onSuccess: (T) -> Unit) {
        when (response) {
            is Result.Error -> {
                showError(response.throwable.localizedMessage
                    ?: "Something went wrong")
            }
            is Result.Success -> {
                onSuccess.invoke(response.data)
            }
        }
    }

}