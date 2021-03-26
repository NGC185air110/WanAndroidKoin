package com.example.wanandroidkoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.model_base.model.ResultResponse
import com.example.model_base.model.login.UserLoginBase
import com.example.wanandroidkoin.databinding.ActivityMainBinding
import org.koin.dsl.module

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private var mainPresenter: MainPresenter? =MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutInflater.from(this).run {
            ActivityMainBinding.inflate(this)
        }
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            mainPresenter?.login("1297740798@qq.com", "zhn820849zhn")
        }
    }

    override fun loginSuccess(userLoginBase: ResultResponse<UserLoginBase>) {
        binding.tvHello.text = userLoginBase.data.toString()
    }

    override fun loginError() {

    }
}