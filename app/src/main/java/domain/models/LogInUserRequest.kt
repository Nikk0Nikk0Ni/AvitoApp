package domain.models

import com.google.gson.annotations.SerializedName

data class LogInUserRequest(
    val email: String,
    val password: String
)
