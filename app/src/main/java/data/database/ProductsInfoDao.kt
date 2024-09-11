package data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.models.ProductDTO
import domain.models.Product

@Dao
interface ProductsInfoDao {
    @Query("SELECT * FROM ProductDTO WHERE id =:productId LIMIT 1")
    suspend fun getProductInfo(productId: String): ProductDTO

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProductInfo(product: ProductDTO)

    @Query("SELECT COUNT(*) FROM ProductDTO WHERE id = :productId")
    suspend fun existsById(productId: String): Int
    companion object{
        const val SINGLE_PRODUCT = 1
    }
}