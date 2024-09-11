package domain.usecases

import androidx.lifecycle.LiveData
import domain.models.Product
import domain.models.ProductDetailResponse
import domain.repository.FakeShopApiRepository
import javax.inject.Inject

class GetProductDetail @Inject constructor(private val repository: FakeShopApiRepository) {
    suspend operator fun invoke(id: String): Product{
        return repository.getProductDetail(id)
    }
}