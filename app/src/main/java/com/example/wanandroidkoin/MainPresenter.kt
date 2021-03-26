package com.example.wanandroidkoin


/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/25 15:21
 */
class MainPresenter constructor(view: MainContract.View) : MainContract.Presenter {

    private val mainModel:MainContract.Model = MainModel(view)
    private val mainView:MainContract.View = view

    override fun login(username: String, password: String) {
        val hashMap: HashMap<String,String> = HashMap()
        hashMap["username"] = username
        hashMap["password"] = password
        mainModel.login(hashMap)
    }
}