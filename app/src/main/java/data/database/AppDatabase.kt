package data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import data.models.ProductDTO

@Database(entities = [ProductDTO::class], version = 2)
@TypeConverters(Converter::class)
abstract class AppDatabase(): RoomDatabase() {
    abstract fun dao(): ProductsInfoDao
    companion object{
        private var db: AppDatabase? = null
        private val lock = Any()
        private const val DB_NAME = "main_db"
        fun getInstance(context: Context): AppDatabase{
            db?.let {
                return it
            }
            synchronized(lock) {
                db?.let {
                    return it
                }
                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }
}