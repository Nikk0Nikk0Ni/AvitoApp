package domain.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
data class Product(
    val id: String = INCORRECT_ID,
    val name: String? = null,
    val category: List<String>? = null,
    val price: Int? = null,
    val discountedPrice: Int? = null,
    val images: List<String>? = null,
    val description: String? = null,
    val productRating: Double? = null,
    val brand: String? = null
){
    companion object{
        const val INCORRECT_ID = "incorrect_id"
    }
}
