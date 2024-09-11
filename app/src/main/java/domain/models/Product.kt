package domain.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class Product(
    @SerializedName("_id")
    @PrimaryKey
    val id: String = INCORRECT_ID,
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
){
    companion object{
        const val INCORRECT_ID = "incorrect_id"
    }
}
