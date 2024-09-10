package domain.usecases

import androidx.lifecycle.LiveData
import domain.models.ProductsResponse
import domain.repository.FakeShopApiRepository
import javax.inject.Inject

class GetProductList @Inject constructor(private val repository: FakeShopApiRepository) {
    suspend operator fun invoke(page: Int): ProductsResponse{
        return repository.getProductList(page)
    }
}