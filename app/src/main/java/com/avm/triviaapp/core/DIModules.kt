package com.avm.triviaapp.core

import android.content.Context
import com.avm.triviaapp.core.network.ConnectivityObserver
import com.avm.triviaapp.core.network.NetworkHandler
import com.avm.triviaapp.data.api.TriviaApiService
import com.avm.triviaapp.data.datasource.TriviaDataSource
import com.avm.triviaapp.data.datasource.TriviaDataSourceImpl
import com.avm.triviaapp.data.repository.TriviaRepository
import com.avm.triviaapp.data.repository.TriviaRepositoryImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * DIModules.kt
 *
 * This file defines the dependency injection modules for the application using Hilt.
 * It provides the necessary dependencies such as repositories, data sources, and API services.
 */
@Module
@InstallIn(SingletonComponent::class)
object DIModules {

    /**
     * Provides the base URL for the API service.
     *
     * @return The base URL as a String.
     */
    @Provides
    fun provideBaseUrl() = "https://opentdb.com/"

    /**
     * Provides the Retrofit instance configured with the base URL and Gson converter.
     *
     * @param baseUrl The base URL for the API.
     * @return The configured Retrofit instance.
     */
    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideConnectivityObserver(
        @ApplicationContext context: Context
    ): ConnectivityObserver {
        return ConnectivityObserver(context)
    }

    @Provides
    @Singleton
    fun provideNetworkHandler(
        connectivityObserver: ConnectivityObserver
    ): NetworkHandler {
        return NetworkHandler(connectivityObserver)
    }

    /**
     * Provides the TriviaApiService for making API calls.
     *
     * @param retrofit The Retrofit instance used to create the service.
     * @return The TriviaApiService instance.
     */
    @Provides
    @Singleton
    fun provideTriviaApiService(retrofit: Retrofit): TriviaApiService {
        return retrofit.create(TriviaApiService::class.java)
    }

    /**
     * Provides the TriviaRepository implementation.
     *
     * @param triviaApiService The api service used to consume data.
     * @return The TriviaRepository implementation.
     */
    @Provides
    @Singleton
    fun provideTriviaDataSource(
        triviaApiService: TriviaApiService,
        networkHandler: NetworkHandler
    ): TriviaDataSource {
        return TriviaDataSourceImpl(triviaApiService, networkHandler)
    }

    /**
     * Provides the TriviaRepository implementation.
     *
     * @param triviaDataSource The data source used to interact with the API.
     * @return The TriviaRepository implementation.
     */
    @Provides
    @Singleton
    fun provideTriviaRepository(triviaDataSource: TriviaDataSource): TriviaRepository {
        return TriviaRepositoryImpl(triviaDataSource)
    }
}