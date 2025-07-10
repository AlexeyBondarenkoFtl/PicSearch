package com.alexeybondarenko.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceFactory {
    fun makeUnsplashApi(): UnsplashApi {
        val baseUrl = "https://api.unsplash.com/"

        val loggingInterceptor = HttpLoggingInterceptor()
        // todo rework setting of logging level
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->

                    val builder = chain.request().newBuilder()
                    builder.addHeader(
                        "Authorization",
                        " Client-ID H1ldbskanohKWvDjGz3U3UhdoDBp1-xx1ut6X4DukKQ"
                    )
                    builder.addHeader(
                        "Accept-Version", "v1"
                    )
                    return@Interceptor chain.proceed(builder.build())
                }
            )
            addInterceptor(loggingInterceptor)
        }.build()

        val retrofit = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(UnsplashApi::class.java)
    }
}