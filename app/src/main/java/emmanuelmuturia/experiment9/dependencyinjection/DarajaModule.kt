package emmanuelmuturia.experiment9.dependencyinjection

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.experiment9.datalayer.remote.DarajaApiService
import emmanuelmuturia.experiment9.datalayer.remote.MyInterceptor
import emmanuelmuturia.experiment9.datalayer.repository.DarajaRepositoryImpl
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DarajaModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {

        val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor = MyInterceptor())
        }.build()

        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        return Retrofit.Builder()
            .baseUrl("https://sandbox.safaricom.co.ke/")
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): DarajaApiService {
        return retrofit.create(DarajaApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesDarajaRepository(darajaApiService: DarajaApiService): DarajaRepositoryImpl {
        return DarajaRepositoryImpl(darajaApiService = darajaApiService)
    }

}