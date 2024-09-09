package com.niko.avitoapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import di.annotation.EmailPatternQualifier
import domain.usecases.RegisterUser
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class RegisterViewModel @Inject constructor(
    private val registerUser: RegisterUser,
    @EmailPatternQualifier
    private val emailPattern: String
) : ViewModel() {
    private val _isEmptyName = MutableLiveData<Boolean>()
    val isEmptyName: LiveData<Boolean>
        get() = _isEmptyName
    private val _isEmptyEmail = MutableLiveData<Boolean>()
    val isEmptyEmail: LiveData<Boolean>
        get() = _isEmptyEmail
    private val _isEmptyPassword = MutableLiveData<Boolean>()
    val isEmptyPassword: LiveData<Boolean>
        get() = _isEmptyPassword
    private val _isEmptyCPassword = MutableLiveData<Boolean>()
    val isEmptyCPassword: LiveData<Boolean>
        get() = _isEmptyCPassword
    private val _isIncorrectEmail = MutableLiveData<Boolean>()
    val isIncorrectEmail: LiveData<Boolean>
        get() = _isIncorrectEmail
    private val _isIncorrectPassword = MutableLiveData<Boolean>()
    val isIncorrectPassword: LiveData<Boolean>
        get() = _isIncorrectPassword
    private val _isntSamePassword = MutableLiveData<Boolean>()
    val isntSamePassword: LiveData<Boolean>
        get() = _isntSamePassword
    private val _isSuccessfulRegistration = MutableLiveData<Boolean>()
    val isSuccessfulRegistration: LiveData<Boolean>
        get() = _isSuccessfulRegistration

    fun register(name: String, email: String, password: String, cPassword: String) {
        if (email.isBlank() || password.isBlank() || name.isBlank() || cPassword.isBlank()) {
            if (email.isBlank())
                _isEmptyEmail.value = true
            if (password.isBlank())
                _isEmptyPassword.value = true
            if (name.isBlank())
                _isEmptyName.value = true
            if(cPassword.isBlank())
                _isEmptyCPassword.value = true
        } else if (!isValidEmail(email) || !isValidPassword(password)) {
            if (!isValidEmail(email))
                _isIncorrectEmail.value = true
            if (!isValidPassword(password))
                _isIncorrectPassword.value = true
        } else if (!isSamePassword(password, cPassword)) {
            _isntSamePassword.value = true
        } else {
            viewModelScope.launch {
                _isSuccessfulRegistration.value = registerUser(name, email, password, cPassword)
            }
        }

    }

    private fun isSamePassword(password: String, cPassword: String) = password == cPassword

    fun resetEmailError() {
        _isEmptyEmail.value = false
        _isIncorrectEmail.value = false
    }

    fun resetPasswordError() {
        _isEmptyPassword.value = false
        _isIncorrectPassword.value = false
        _isntSamePassword.value = false
        _isEmptyCPassword.value = false
    }


    fun resetNameError(){
        _isEmptyName.value = false
    }

    private fun isValidEmail(email: String): Boolean {
        val regular = emailPattern.toRegex()
        return regular.matches(email)
    }

    private fun isValidPassword(password: String) = password.length in 8..24

}