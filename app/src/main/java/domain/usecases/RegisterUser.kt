package domain.usecases

import domain.repository.FakeShopApiRepository

class RegisterUser(private val repository: FakeShopApiRepository) {
    suspend operator fun invoke(name: String, email: String, password: String, cpassword: String, callbackIsRegister: ((Boolean)->Unit)){
        repository.registerUser(name,email,password,cpassword,callbackIsRegister)
    }
}