package com.simple.kotlin.simplekotlin.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.simple.kotlin.simplekotlin.R

class BodyFragment : Fragment () {

    private var bodyText : TextView? = null

    private val viewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        var root = inflater.inflate(R.layout.body_fragment, container, false);
        bodyText = root.findViewById(R.id.body_text)
        return root
    }


    override fun onStart() {
        super.onStart()

        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
                .getClickObserver().observe( this, Observer { value ->
                    bodyText?.text = value
                })
    }
}