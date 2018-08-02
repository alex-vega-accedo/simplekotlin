package com.simple.kotlin.simplekotlin.ui

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.simple.kotlin.simplekotlin.R

class MainActivity : FragmentActivity () {

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstance : Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.main_activity)

        val bodyContainer: View? = findViewById(R.id.body_container)

        if (savedInstance == null) {
            if (bodyContainer == null) {
                viewModel.setState(MainViewModel.State.TITLE)
            }
        }

        var titleFragment : TitleFragment = TitleFragment()

        if (bodyContainer == null) {
            if (viewModel.getState() == MainViewModel.State.TITLE) {
                supportFragmentManager.beginTransaction().replace(R.id.main_container, TitleFragment()).commit()
            } else {
                supportFragmentManager.beginTransaction().replace(R.id.main_container, BodyFragment()).commit()
            }
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.main_container, TitleFragment()).commit()
            supportFragmentManager.beginTransaction().replace(R.id.body_container, BodyFragment()).commit()
        }

        viewModel.getClickObserver().observe(this, Observer { position ->
            if (bodyContainer == null && viewModel.getState() == MainViewModel.State.TITLE) {
                supportFragmentManager.beginTransaction().replace(R.id.main_container, BodyFragment()).commit()
                viewModel.setState(MainViewModel.State.BODY)
            }
        })

    }




}