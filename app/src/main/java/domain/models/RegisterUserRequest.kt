package domain.models

import com.google.gson.annotations.SerializedName

data class RegisterUserRequest(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("photo")
    val photo: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("cpassword")
    val cpassword: String? = null,
    @SerializedName("isAdmin")
    val isAdmin: Boolean = false,
    @SerializedName("address")
    val address : RegisterAddressRequest,
    @SerializedName("phone")
    val phone: String? = null
)