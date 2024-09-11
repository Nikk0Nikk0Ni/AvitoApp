package data.models

import com.google.gson.annotations.SerializedName

data class ProductDetailResponseDTO(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("data")
    val data: ProductDTO
) {
    companion object {
        const val STATUS_SUCCESSFUL = "success"
    }
}

