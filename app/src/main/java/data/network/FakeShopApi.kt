package data.network

import androidx.lifecycle.LiveData
import data.models.LogInUserResponseDTO
import data.models.ProductDetailResponseDTO
import data.models.ProductsResponseDTO
import data.models.RegisterResponseDTO
import domain.models.LogInUserRequest
import domain.models.LogInUserResponse
import domain.models.Product
import domain.models.ProductDetailResponse
import domain.models.ProductsResponse
import domain.models.RegisterResponse
import domain.models.RegisterUserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FakeShopApi {
    @POST("users")
    suspend fun addUser(@Body user: RegisterUserRequest): RegisterResponseDTO

    @POST("users/auth/login")
    suspend fun logInUser(@Body user: LogInUserRequest): LogInUserResponseDTO

    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int,
        @Query("limit") limit: Int = DEFAULT_LIMIT
    ): ProductsResponseDTO

    @GET("products")
    suspend fun getProductsByCategory(
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int = DEFAULT_LIMIT
    ): ProductsResponseDTO

    @GET("products")
    suspend fun sortByPriceCategoryProduct(
        @Query("sort", encoded = true) price: String,
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int = DEFAULT_LIMIT
    ): ProductsResponseDTO

    @GET("products")
    suspend fun sortByPriceProduct(
        @Query("sort", encoded = true) price: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int = DEFAULT_LIMIT
    ): ProductsResponseDTO

    @GET("products/{id}")
    suspend fun getProductDetail(
        @Path("id") id: String
    ): ProductDetailResponseDTO

    companion object {
        private const val DEFAULT_LIMIT = 30
        const val SORT_MAX_TO_MIN = "-price"
        const val SORT_MIN_TO_MAX = "+price"
    }
}