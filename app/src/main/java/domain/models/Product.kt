package domain.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("category")
    val category: List<String>? = null,
    @SerializedName("price")
    val price: Int? = null,
    @SerializedName("discounted_price")
    val discountedPrice: Int? = null,
    @SerializedName("images")
    val images: List<String>? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("product_rating")
    val productRating: Double? = null,
    @SerializedName("brand")
    val brand: String? = null
)
