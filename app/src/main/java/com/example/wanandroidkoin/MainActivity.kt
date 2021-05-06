package com.example.wanandroidkoin

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.model_base.model.ResultResponse
import com.example.model_base.model.login.UserLoginBase
import com.example.model_base.utils.RouteUtils
import com.example.wanandroidkoin.databinding.ActivityMainBinding

@Route(path = RouteUtils.Activity_Home)
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
        binding.btnFavorites.setOnClickListener {
            mainPresenter?.getFavorites()
        }
        var a: Long = 197_812_309_120_931
    }

    override fun loginSuccess(userLoginBase: ResultResponse<UserLoginBase>) {
        binding.tvHello.text = userLoginBase.data?.nickname
    }

    override fun loginError() {

    }

    override fun getFavoritesSuccess(favorites: ResultResponse<Any>) {
        TODO("Not yet implemented")
    }

    override fun getFavoritesError() {
        TODO("Not yet implemented")
    }
}