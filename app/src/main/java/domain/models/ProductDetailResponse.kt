package domain.models

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    val status: String? = null,
    val data: Product
) {
    companion object {
        const val STATUS_SUCCESSFUL = "success"
    }
}

