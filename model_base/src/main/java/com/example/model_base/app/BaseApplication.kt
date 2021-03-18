package com.example.model_base.app

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.example.model_base.BuildConfig

/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/18 13:31
 */
class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        initARouter(this@BaseApplication)
    }

    fun initARouter(application: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(application)
    }
}