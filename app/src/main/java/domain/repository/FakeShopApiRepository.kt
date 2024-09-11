package domain.repository

import androidx.lifecycle.LiveData
import domain.models.Product
import domain.models.ProductDetailResponse
import domain.models.ProductsResponse
import domain.usecases.GetProductDetail

interface FakeShopApiRepository {
    suspend fun registerUser(
        name: String,
        email: String,
        password: String,
        cpassword: String
    ): Boolean

    suspend fun logInUser(email: String, password: String): Boolean
    suspend fun getProductList(page: Int): ProductsResponse
    suspend fun sortByPriceCategoryProduct(sort: String, category: String,page: Int): ProductsResponse
    suspend fun sortByPriceProduct(sort: String,page: Int): ProductsResponse
    suspend fun getProductListByCategory(category: String, page: Int): ProductsResponse
    suspend fun getProductDetail(id: String): Product
}