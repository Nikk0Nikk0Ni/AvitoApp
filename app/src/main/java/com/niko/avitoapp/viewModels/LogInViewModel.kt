package com.niko.avitoapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niko.avitoapp.R
import di.annotation.EmailPatternQualifier
import domain.usecases.LogInUser
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUser,
    @EmailPatternQualifier
    private val emailPattern: String
) : ViewModel() {
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
    private val _userFound = MutableLiveData<Boolean>()
    val userFound: MutableLiveData<Boolean>
        get() = _userFound

    fun logInUser(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            if (email.isBlank())
                _isEmptyEmail.value = true
            if (password.isBlank())
                _isEmptyPassword.value = true
        } else if (!isValidEmail(email) || !isValidPassword(password)) {
            if (!isValidEmail(email))
                _isIncorrectEmail.value = true
            if (!isValidPassword(password))
                _isIncorrectPassword.value = true
        } else{
            viewModelScope.launch {
                _userFound.value = logInUseCase(email,password)
            }
        }
    }

    fun resetEmailError() {
        _isEmptyEmail.value = false
        _isIncorrectEmail.value = false
    }

    fun resetPasswordError() {
        _isEmptyPassword.value = false
        _isIncorrectPassword.value = false
    }

    private fun isValidEmail(email: String): Boolean {
        val regular = emailPattern.toRegex()
        return regular.matches(email)
    }

    private fun isValidPassword(password: String) = password.length <= 24
}