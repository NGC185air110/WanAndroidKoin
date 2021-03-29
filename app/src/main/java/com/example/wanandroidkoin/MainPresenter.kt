package com.example.wanandroidkoin

import com.example.model_base.base.BaseObserver
import com.example.model_base.base.BasePresenter
import com.example.model_base.model.ResultResponse
import com.example.model_base.model.login.UserLoginBase


/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/25 15:21
 */
class MainPresenter constructor(view: MainContract.View) : MainContract.Presenter,
    BasePresenter<MainContract.View>(view) {

    private val mainView: MainContract.View = view

    override fun login(username: String, password: String) {
        val hashMap: HashMap<String, String> = HashMap()
        hashMap["username"] = username
        hashMap["password"] = password
        addSubscription(mApi.userLogin(hashMap),
            object : BaseObserver<ResultResponse<UserLoginBase>>() {
                override fun onNext(t: ResultResponse<UserLoginBase>) {
                    super.onNext(t)
                    mainView.loginSuccess(t)
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    mainView.loginError()
                }
            })
    }

    override fun getFavorites() {
        addSubscription(mApi.getFavorites("/0/json"), object : BaseObserver<ResultResponse<Any>>() {
            override fun onNext(t: ResultResponse<Any>) {
                super.onNext(t)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
            }
        })
    }
}