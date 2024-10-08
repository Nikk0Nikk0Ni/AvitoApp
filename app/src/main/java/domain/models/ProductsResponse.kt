package domain.models

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    val status: String? = null,
    val count: Int? = null,
    val data: List<Product> = listOf()
) {
    companion object {
        const val STATUS_SUCCESSFUL = "success"
    }
}
