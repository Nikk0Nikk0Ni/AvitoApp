package data.network

import android.content.Context
import di.annotation.ApplicationScope
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
@ApplicationScope
class AuthInterceptor @Inject constructor(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("auth_token", null)
        val request = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }

        return chain.proceed(request)
    }
}