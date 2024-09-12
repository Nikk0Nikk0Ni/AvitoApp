package domain.usecases

import androidx.lifecycle.LiveData
import domain.models.Product
import domain.models.ProductDetailResponse
import domain.repository.FakeShopApiRepository
import javax.inject.Inject

class GetProductDetail @Inject constructor(private val repository: FakeShopApiRepository) {
    suspend operator fun invoke(id: String, callback: (()->Unit)): Product{
        return repository.getProductDetail(id,callback)
    }
}