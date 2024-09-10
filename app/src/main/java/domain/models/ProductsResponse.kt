package domain.models

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("Data")
    val data: List<Product> = listOf()
) {
    companion object {
        const val STATUS_SUCCESSFUL = "success"
    }
}
