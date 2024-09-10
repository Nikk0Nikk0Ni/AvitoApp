package domain.usecases

import domain.models.ProductsResponse
import domain.repository.FakeShopApiRepository
import javax.inject.Inject

class SortByPriceCategoryProduct @Inject constructor(private val repository: FakeShopApiRepository) {
    suspend operator fun invoke(sort: String,category: String,page: Int): ProductsResponse{
        return repository.sortByPriceCategoryProduct(sort, category,page)
    }
}