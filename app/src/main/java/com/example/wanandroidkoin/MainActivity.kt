package com.example.wanandroidkoin

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.model_base.base.BaseActivity
import com.example.model_base.model.ResultResponse
import com.example.model_base.model.login.UserLoginBase
import com.example.model_base.utils.RouteUtils
import com.example.wanandroidkoin.databinding.ActivityMainBinding

@Route(path = RouteUtils.Activity_Home)
class MainActivity : BaseActivity<ActivityMainBinding>(), MainContract.View {
    private var mainPresenter: MainPresenter? = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun loginSuccess(userLoginBase: ResultResponse<UserLoginBase>) {
        binding.tvHello.text = userLoginBase.data?.nickname
    }

    override fun loginError() {

    }

    override fun getFavoritesSuccess(favorites: ResultResponse<Any>) {}

    override fun getFavoritesError() {}

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {}

    override fun initListener() {
        setOnClick(binding.btnLogin, binding.btnFavorites)
    }

    //测试下git11111111
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                mainPresenter?.login("1297740798@qq.com", "zhn820849zhn")
            }
            R.id.btn_favorites -> {
                mainPresenter?.getFavorites()
            }
        }
    }
}
