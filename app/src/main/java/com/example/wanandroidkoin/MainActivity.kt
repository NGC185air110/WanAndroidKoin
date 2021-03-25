package com.example.wanandroidkoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.dsl.module

class MainActivity : AppCompatActivity(),MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}