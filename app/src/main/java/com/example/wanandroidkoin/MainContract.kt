package com.example.wanandroidkoin

import com.example.model_base.model.ResultResponse
import com.example.model_base.model.login.UserLoginBase

/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/25 15:19
 */
interface MainContract {
    interface Presenter {
        fun login(username: String, password: String)
        fun getFavorites()
    }
    interface View {
        fun loginSuccess(userLoginBase: ResultResponse<UserLoginBase>)
        fun loginError()
        fun getFavoritesSuccess(favorites: ResultResponse<Any>)
        fun getFavoritesError()
    }
}