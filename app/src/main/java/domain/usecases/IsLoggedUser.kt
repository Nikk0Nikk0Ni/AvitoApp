package domain.usecases

import domain.repository.FakeShopApiRepository
import javax.inject.Inject

class IsLoggedUser @Inject constructor(private val repository: FakeShopApiRepository) {
    operator fun invoke(): Boolean = repository.isLoggedUser()
}