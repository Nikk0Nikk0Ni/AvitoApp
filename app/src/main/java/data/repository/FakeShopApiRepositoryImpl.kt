package data.repository

import android.content.Context
import android.util.Log
import data.database.ProductsInfoDao
import data.mappers.FakeApiDataMapper
import data.models.AuthUserResponseDTO
import data.models.LogInUserResponseDTO
import data.models.ProductDTO
import data.models.ProductDetailResponseDTO
import data.network.FakeShopApi
import di.annotation.ApplicationScope
import domain.models.AuthUserResponse
import domain.models.LogInUserRequest
import domain.models.LogInUserResponse
import domain.models.Product
import domain.models.ProductDetailResponse
import domain.models.ProductsResponse
import domain.models.RegisterAddressRequest
import domain.models.RegisterUserRequest
import domain.repository.FakeShopApiRepository
import javax.inject.Inject

@ApplicationScope
class FakeShopApiRepositoryImpl @Inject constructor(
    private val api: FakeShopApi,
    private val productsDB: ProductsInfoDao,
    private val context: Context,
    private val mapper: FakeApiDataMapper
) :
    FakeShopApiRepository {
    override suspend fun registerUser(
        name: String,
        email: String,
        password: String,
        cpassword: String,
    ): Boolean {
        try {
            val response = api.addUser(
                RegisterUserRequest(
                    name = name,
                    email = email,
                    password = password,
                    cpassword = cpassword,
                    address = RegisterAddressRequest()
                )
            )
            return response.status == AuthUserResponseDTO.STATUS_SUCCESSFUL
        } catch (e: Exception) {
            Log.e("AUF", "Error: ${e.message}")
            return false
        }
    }

    override suspend fun logInUser(email: String, password: String): Boolean {
        try {
            val response = api.logInUser(LogInUserRequest(email, password))
            return if (response.status == LogInUserResponseDTO.STATUS_SUCCESSFUL){
                response.token?.let {
                    saveToken(it)
                }
                true
            }else false
        } catch (e: Exception) {
            Log.e("AUF", "${e.message}")
            return false
        }
    }

    private fun saveToken(token: String) {
        val sharedPreference = context.getSharedPreferences("app_preference",Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.apply {
            putString("auth_token",token)
            apply()
        }
    }

    override suspend fun getProductList(page: Int): ProductsResponse {
        return try {
            mapper.mapProductsResponseDTOToProductsResponse(api.getProducts(page))
        } catch (e: Exception) {
            Log.e("AUF", "${e.message}")
            ProductsResponse()
        }

    }

    override suspend fun sortByPriceCategoryProduct(
        sort: String,
        category: String,
        page: Int
    ): ProductsResponse {
        return try {
            mapper.mapProductsResponseDTOToProductsResponse(api.sortByPriceCategoryProduct(sort, category, page))
        } catch (e: Exception) {
            Log.e("AUF", "${e.message}")
            ProductsResponse()
        }
    }

    override suspend fun sortByPriceProduct(sort: String, page: Int): ProductsResponse {
        return try {
            mapper.mapProductsResponseDTOToProductsResponse(api.sortByPriceProduct(sort, page))
        } catch (e: Exception) {
            Log.e("AUF", "${e.message}")
            ProductsResponse()
        }
    }

    override suspend fun getProductListByCategory(category: String, page: Int): ProductsResponse {
        return try {
            mapper.mapProductsResponseDTOToProductsResponse(api.getProductsByCategory(category, page))
        } catch (e: Exception) {
            Log.e("AUF", "${e.message}")
            ProductsResponse()
        }
    }

    override suspend fun getProductDetail(id: String): Product {
        return try {
            if (productsDB.existsById(id) == ProductsInfoDao.SINGLE_PRODUCT ) {
                mapper.mapProductDTOPToProduct(productsDB.getProductInfo(id))
            }
            else {
                val productResponse = api.getProductDetail(id)
                if (productResponse.status == ProductDetailResponseDTO.STATUS_SUCCESSFUL) {
                    productsDB.insertProductInfo(productResponse.data)
                    mapper.mapProductDTOPToProduct(productsDB.getProductInfo(id))
                }else
                    Product()
            }
        } catch (e: Exception) {
            Log.e("AUF", "${e.message}")
            Product()
        }
    }

    override fun isLoggedUser(): Boolean {
        val sharedPreferences = context.getSharedPreferences("app_preference", Context.MODE_PRIVATE)
        return sharedPreferences.getString("auth_token", null) != null
    }
}