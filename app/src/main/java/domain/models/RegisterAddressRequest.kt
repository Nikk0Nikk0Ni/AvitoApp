package domain.models

import com.google.gson.annotations.SerializedName

data class RegisterAddressRequest(
    @SerializedName("street")
    val street: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("state")
    val state: String? = null,
    @SerializedName("postalCode")
    val postalCode: String? = null,
    @SerializedName("country")
    val country: String? = null,
    ) {
    companion object {
        const val STATUS_SUCCESSFUL = "success"
    }
}
