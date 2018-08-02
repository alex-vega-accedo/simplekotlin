package com.simple.kotlin.simplekotlin.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.support.annotation.MainThread
import android.util.Log
import com.simple.kotlin.simplekotlin.KotlinApplication
import com.simple.kotlin.simplekotlin.adapters.PostListAdatper
import com.simple.kotlin.simplekotlin.model.Post
import com.simple.kotlin.simplekotlin.networking.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel : PostListAdatper.ItemClick, ViewModel()  {

    enum class State {
        TITLE,
        BODY
    }

    private var state : State? = null
    private lateinit var postApi : PostApi
    private var postListAdapter : PostListAdatper? = null
    private val postListObserver = MutableLiveData<PostListAdatper>()
    private val itemClick = MutableLiveData<String>()

    init {
        postApi = KotlinApplication.injectPostApi()

    }

    fun getPostList() : MutableLiveData<PostListAdatper> {
        if(postListAdapter == null){
            startNetworkRequest()
        }

        return postListObserver
    }

    fun startNetworkRequest(){

        postApi.getPosts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { result ->
                    postListAdapter = PostListAdatper(result, this)
                    postListObserver.value = postListAdapter
                }, { onError -> Log.e(" error", " : " + onError.message)
                })
    }

    fun getState () : State? {
        return state
    }

    fun setState(state : State?){
        this.state = state
    }

    override fun onClick(position: Int) {
        itemClick.value = postListAdapter?.getItemValue(position)
    }

    fun getClickObserver() : MutableLiveData<String> {
        return itemClick;
    }


}