package data.models

import com.google.gson.annotations.SerializedName

data class LogInUserRequestDTO(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
