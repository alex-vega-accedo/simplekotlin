package com.simple.kotlin.simplekotlin.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simple.kotlin.simplekotlin.R


class TitleFragment : Fragment (){

    private lateinit var recyclerView : RecyclerView

    private val viewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        var root = inflater.inflate(R.layout.title_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        return root
    }


    override fun onStart() {
        super.onStart()

        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
                .getPostList().observe( this, Observer { postListAdapter ->
                    recyclerView.adapter = postListAdapter

                })
    }
}