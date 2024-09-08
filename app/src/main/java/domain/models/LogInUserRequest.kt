package domain.models

import com.google.gson.annotations.SerializedName

data class LogInUserRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
