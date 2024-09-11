package data.network

import android.content.Context
import di.annotation.ApplicationScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
@ApplicationScope
class RetrofitClient @Inject constructor(context: Context) {
    private val authInterceptor = AuthInterceptor(context)
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(authInterceptor)
        .build()
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
    val api = retrofit.create(FakeShopApi::class.java)
    companion object{
        private const val BASE_URL = "https://fakeshopapi-l2ng.onrender.com/app/v1/"
    }
}