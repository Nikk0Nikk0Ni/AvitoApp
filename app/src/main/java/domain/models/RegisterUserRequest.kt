package domain.models

import com.google.gson.annotations.SerializedName

data class RegisterUserRequest(
    val name: String? = null,
    val email: String? = null,
    val photo: String? = null,
    val password: String? = null,
    val cpassword: String? = null,
    val isAdmin: Boolean = false,
    val address : RegisterAddressRequest,
    val phone: String? = null
)