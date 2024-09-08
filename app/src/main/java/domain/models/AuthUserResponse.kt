package domain.models

import com.google.gson.annotations.SerializedName

data class AuthUserResponse(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("token")
    val token: String? = null,

){
    companion object{
        const val STATUS_SUCCESSFUL = "success"
    }
}