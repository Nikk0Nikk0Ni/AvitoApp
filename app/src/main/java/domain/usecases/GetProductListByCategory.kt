package domain.usecases

import domain.models.ProductsResponse
import domain.repository.FakeShopApiRepository
import javax.inject.Inject

class GetProductListByCategory @Inject constructor(private val repository: FakeShopApiRepository) {
    suspend operator fun invoke(category: String, page: Int): ProductsResponse {
        return repository.getProductListByCategory(category,page)
    }
}