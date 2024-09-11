package data.models

import com.google.gson.annotations.SerializedName

data class RegisterResponseDTO(
    @SerializedName("status")
    val status: String? = null
)