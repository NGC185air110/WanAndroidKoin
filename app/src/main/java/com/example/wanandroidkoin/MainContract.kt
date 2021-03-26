package com.example.wanandroidkoin

/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/25 15:19
 */
interface MainContract {
    interface Model {
        fun login(map: Map<String, String>)
    }
    interface Presenter {
        fun login(username: String, password: String)
    }
    interface View {

    }
}