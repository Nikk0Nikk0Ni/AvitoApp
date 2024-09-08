package domain.repository

interface FakeShopApiRepository {
    suspend fun registerUser(name: String,email: String,password: String, cpassword: String,callbackIsRegister: ((Boolean)->Unit))
    suspend fun logInUser(email: String,password: String): Boolean
}