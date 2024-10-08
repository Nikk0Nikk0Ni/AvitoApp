package domain.usecases

import domain.repository.FakeShopApiRepository
import javax.inject.Inject

class LogInUser @Inject constructor(private val repository: FakeShopApiRepository) {
    suspend operator fun invoke(email: String,password: String, callbackError: (()->Unit)): Boolean{
        return repository.logInUser(email,password,callbackError)
    }
}