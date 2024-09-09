package domain.usecases

import domain.repository.FakeShopApiRepository
import javax.inject.Inject

class RegisterUser @Inject constructor(private val repository: FakeShopApiRepository) {
    suspend operator fun invoke(name: String, email: String, password: String, cpassword: String): Boolean{
        return repository.registerUser(name,email,password,cpassword)
    }
}