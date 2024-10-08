package domain.models

import com.google.gson.annotations.SerializedName

data class RegisterAddressRequest(
    val street: String? = null,
    val city: String? = null,
    val state: String? = null,
    val postalCode: String? = null,
    val country: String? = null,
    ) {
    companion object {
        const val STATUS_SUCCESSFUL = "success"
    }
}
