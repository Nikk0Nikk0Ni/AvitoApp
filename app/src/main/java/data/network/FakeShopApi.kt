package data.network

import domain.models.LogInUserRequest
import domain.models.LogInUserResponse
import domain.models.RegisterResponse
import domain.models.RegisterUserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface FakeShopApi {
    @POST("users")
    suspend fun addUser(@Body user: RegisterUserRequest): RegisterResponse

    @POST("users/auth/login")
    suspend fun logInUser(@Body user: LogInUserRequest): LogInUserResponse
}