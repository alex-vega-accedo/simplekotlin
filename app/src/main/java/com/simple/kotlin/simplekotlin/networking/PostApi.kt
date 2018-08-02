package com.simple.kotlin.simplekotlin.networking

import io.reactivex.Observable
import com.simple.kotlin.simplekotlin.model.Post
import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    fun getPosts() : Observable<List<Post>>
}