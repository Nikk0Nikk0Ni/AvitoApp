package data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import domain.models.Product

@Dao
interface ProductsInfoDao {
    @Query("SELECT * FROM Product WHERE id =:productId LIMIT 1")
    suspend fun getProductInfo(productId: String): Product

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProductInfo(product: Product)

    @Query("SELECT COUNT(*) FROM Product WHERE id = :productId")
    suspend fun existsById(productId: String): Int
}