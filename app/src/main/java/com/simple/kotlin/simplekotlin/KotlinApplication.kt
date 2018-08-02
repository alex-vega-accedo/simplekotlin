package com.simple.kotlin.simplekotlin

import android.app.Application
import com.simple.kotlin.simplekotlin.utils.BASE_URL
import io.reactivex.schedulers.Schedulers
import com.simple.kotlin.simplekotlin.networking.PostApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class KotlinApplication : Application() {


    companion object {
        private lateinit var retrofit : Retrofit
        private lateinit var postApi: PostApi

        fun injectPostApi() = postApi

    }

    override fun onCreate() {
        super.onCreate()


        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

        postApi = retrofit.create(PostApi::class.java)
    }

}