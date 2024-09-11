package domain.models

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("data")
    val data: Product
) {
    companion object {
        const val STATUS_SUCCESSFUL = "success"
    }
}

