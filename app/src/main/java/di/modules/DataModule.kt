package di.modules

import android.content.Context
import com.niko.avitoapp.R
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import data.database.AppDatabase
import data.database.ProductsInfoDao
import data.network.FakeShopApi
import data.network.RetrofitClient
import data.repository.FakeShopApiRepositoryImpl
import di.annotation.ApplicationScope
import di.annotation.EmailPatternQualifier
import domain.repository.FakeShopApiRepository

@Module
interface DataModule{

    companion object{
        @ApplicationScope
        @Provides
        fun provideFakeShopApi(): FakeShopApi{
            return RetrofitClient.api
        }
        @ApplicationScope
        @EmailPatternQualifier
        @Provides
        fun provideEmailPattern(context: Context): String{
            return context.getString(R.string.emailRegularPattern)
        }

        @ApplicationScope
        @Provides
        fun provideProductsInfoDao(context: Context): ProductsInfoDao{
            return AppDatabase.getInstance(context).dao()
        }

    }
}