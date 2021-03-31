package com.example.model_base.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Handler
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.example.model_base.BuildConfig
import com.orhanobut.logger.Logger
import com.tencent.mmkv.MMKV
import java.util.*
import kotlin.properties.Delegates

/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/18 13:31
 */
class BaseApplication : MultiDexApplication() {
    var sessionId:String?=null//sessionid

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        instance =this
        initARouter(this@BaseApplication)
        initMMKV()
    }

    companion object {
        var instance: BaseApplication? = null

        var context: Context by Delegates.notNull()

        private var mHandler: Handler? = null//主线程Handler
        //运用list来保存们每一个activity是关键
        private val mList = LinkedList<Activity>()
    }

    fun initARouter(application: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(application)
    }

    fun initMMKV(){
        val rootDir:String = MMKV.initialize(this@BaseApplication)
        Logger.d("mmkv root:  $rootDir")
    }
}