package com.example.wanandroidkoin

import com.example.model_base.base.BaseModel
import com.example.model_base.base.BaseObserver
import com.example.model_base.model.ResultResponse
import com.example.model_base.model.login.UserLoginBase

/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/25 15:35
 */
class MainModel constructor(view: MainContract.View) : MainContract.Model,
    BaseModel<MainContract.View>(view) {
    fun userLogin(map: Map<String, String>) {
        addSubscription(mApi.userLogin(getJsonRequestBody(map)),
            object : BaseObserver<ResultResponse<UserLoginBase>>() {
                override fun onNext(t: ResultResponse<UserLoginBase>) {
                    super.onNext(t)
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                }
            })
    }
}