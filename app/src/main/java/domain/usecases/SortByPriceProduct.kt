package domain.usecases

import domain.models.ProductsResponse
import domain.repository.FakeShopApiRepository
import javax.inject.Inject

class SortByPriceProduct @Inject constructor(private val repository: FakeShopApiRepository) {
    suspend operator fun invoke(sort: String,page: Int): ProductsResponse{
        return repository.sortByPriceProduct(sort,page)
    }
}