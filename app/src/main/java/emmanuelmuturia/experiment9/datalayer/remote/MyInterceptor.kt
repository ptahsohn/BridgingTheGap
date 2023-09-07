package emmanuelmuturia.experiment9.datalayer.remote

import emmanuelmuturia.experiment9.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(name = BuildConfig.authorisationName, value = BuildConfig.authorisationValue)
            .build()
        return chain.proceed(request = request)
    }
}