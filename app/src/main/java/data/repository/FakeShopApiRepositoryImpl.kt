package data.repository

import android.util.Log
import data.network.RetrofitClient
import domain.models.AuthUserResponse
import domain.models.LogInUserRequest
import domain.models.LogInUserResponse
import domain.models.RegisterAddressRequest
import domain.models.RegisterResponse
import domain.models.RegisterUserRequest
import domain.repository.FakeShopApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FakeShopApiRepositoryImpl : FakeShopApiRepository {
    override suspend fun registerUser(
        name: String,
        email: String,
        password: String,
        cpassword: String,
        callbackIsRegister: ((Boolean)->Unit)
    ) {
        try {
            val response = RetrofitClient.api.addUser(
                RegisterUserRequest(
                    name = name,
                    email = email,
                    password = password,
                    cpassword = cpassword,
                    address = RegisterAddressRequest()
                )
            )
            if (response.status == AuthUserResponse.STATUS_SUCCESSFUL) {
                callbackIsRegister(true)
            } else {
                callbackIsRegister(false)
            }
        } catch (e: Exception) {
            Log.e("AUF", "Error: ${e.message}")
        }
    }

    override suspend fun logInUser(email: String, password: String): Boolean {
        try {
            val response = RetrofitClient.api.logInUser(LogInUserRequest(email,password))
            return response.status == LogInUserResponse.STATUS_SUCCESSFUL
        }catch (e: Exception){
            Log.e("AUF","${e.message}")
            return false
        }
    }
}