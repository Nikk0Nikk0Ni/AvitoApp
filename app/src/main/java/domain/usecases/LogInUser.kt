package domain.usecases

import domain.repository.FakeShopApiRepository

class LogInUser(private val repository: FakeShopApiRepository) {
    suspend operator fun invoke(email: String,password: String): Boolean{
        return repository.logInUser(email,password)
    }
}