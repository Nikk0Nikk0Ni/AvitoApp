package data.models

import com.google.gson.annotations.SerializedName

data class ProductsResponseDTO(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("Data")
    val data: List<ProductDTO> = listOf()
) {
    companion object {
        const val STATUS_SUCCESSFUL = "success"
    }
}
