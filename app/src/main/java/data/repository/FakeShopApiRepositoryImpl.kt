package data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import data.network.FakeShopApi
import data.network.RetrofitClient
import di.annotation.ApplicationScope
import domain.models.AuthUserResponse
import domain.models.LogInUserRequest
import domain.models.LogInUserResponse
import domain.models.ProductDetailResponse
import domain.models.ProductsResponse
import domain.models.RegisterAddressRequest
import domain.models.RegisterResponse
import domain.models.RegisterUserRequest
import domain.repository.FakeShopApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@ApplicationScope
class FakeShopApiRepositoryImpl @Inject constructor(private val api: FakeShopApi) :
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
            return response.status == AuthUserResponse.STATUS_SUCCESSFUL
        } catch (e: Exception) {
            Log.e("AUF", "Error: ${e.message}")
            return false
        }
    }

    override suspend fun logInUser(email: String, password: String): Boolean {
        try {
            val response = api.logInUser(LogInUserRequest(email, password))
            return response.status == LogInUserResponse.STATUS_SUCCESSFUL
        } catch (e: Exception) {
            Log.e("AUF", "${e.message}")
            return false
        }
    }

    override suspend fun getProductList(page: Int): ProductsResponse {
        return try {
            api.getProducts(page)
        } catch (e: Exception) {
            Log.e("AUF", "${e.message}")
            ProductsResponse()
        }

    }

    override suspend fun sortByPriceCategoryProduct(sort: String, category:String,page: Int): ProductsResponse {
        return try {
            api.sortByPriceCategoryProduct(sort,category ,page)
        } catch (e: Exception) {
            Log.e("AUF", "${e.message}")
            ProductsResponse()
        }
    }

    override suspend fun sortByPriceProduct(sort: String, page: Int): ProductsResponse {
        return try {
            api.sortByPriceProduct(sort ,page)
        } catch (e: Exception) {
            Log.e("AUF", "${e.message}")
            ProductsResponse()
        }
    }

    override suspend fun getProductListByCategory(category: String, page: Int): ProductsResponse {
        return try {
            api.getProductsByCategory(category, page)
        } catch (e: Exception) {
            Log.e("AUF", "${e.message}")
            ProductsResponse()
        }
    }

    override suspend fun getProductDetail(id: String): ProductDetailResponse {
        return try{
            api.getProductDetail(id)
        }catch (e: Exception){
            Log.e("AUF", "${e.message}")
            ProductDetailResponse()
        }
    }
}