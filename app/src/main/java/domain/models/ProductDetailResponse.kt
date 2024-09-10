package domain.models

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("Data")
    val data: List<Product> = listOf()
) {
    companion object {
        const val STATUS_SUCCESSFUL = "success"
    }
}

