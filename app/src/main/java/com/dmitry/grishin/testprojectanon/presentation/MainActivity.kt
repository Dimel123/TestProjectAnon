package com.dmitry.grishin.testprojectanon.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router

import com.dmitry.grishin.testprojectanon.R
import kotlinx.android.synthetic.main.activity_main.*
import com.bluelinelabs.conductor.RouterTransaction
import com.dmitry.grishin.testprojectanon.presentation.main.MainController


class MainActivity : AppCompatActivity() {

    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router = Conductor.attachRouter(this, container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(MainController()))
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

}
