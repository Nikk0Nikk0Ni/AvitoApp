package com.niko.avitoapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.usecases.RegisterUser
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class RegisterViewModel @Inject constructor(
    private val registerUser: RegisterUser
): ViewModel() {
    private val _isEmptyEmail = MutableLiveData<Boolean>()
    val isEmptyEmail: LiveData<Boolean>
        get() = _isEmptyEmail
    private val _isEmptyPassword = MutableLiveData<Boolean>()
    val isEmptyPassword: LiveData<Boolean>
        get() = _isEmptyPassword
    private val _isIncorrectEmail = MutableLiveData<Boolean>()
    val isIncorrectEmail: LiveData<Boolean>
        get() = _isIncorrectEmail
    private val _isIncorrectPassword = MutableLiveData<Boolean>()
    val isIncorrectPassword: LiveData<Boolean>
        get() = _isIncorrectPassword
    fun register(name: String,email:String,password:String,cPassword:String){
        viewModelScope.launch {
            registerUser(name,email,password,cPassword)
        }
    }

}