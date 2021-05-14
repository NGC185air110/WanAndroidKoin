package com.example.model_base.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Handler
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.example.model_base.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
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
        initLogger()
        initMMKV()
    }

    companion object {
        var instance: BaseApplication? = null

        var context: Context by Delegates.notNull()

        private var mHandler: Handler? = null//主线程Handler
        //运用list来保存们每一个activity是关键
        private val mList = LinkedList<Activity>()
    }

    private fun initARouter(application: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(application)
    }

    private fun initMMKV(){
        val rootDir:String = MMKV.initialize(this@BaseApplication)
        Logger.d("mmkv root:  $rootDir")
    }

    private fun initLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)  // 隐藏线程信息 默认：显示
            .methodCount(0)         // 决定打印多少行（每一行代表一个方法）默认：2
            .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
            .tag("WanAndroid")   // (Optional) Global tag for every log. Default PRETTY_LOGGER,设置TAG
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                if (BuildConfig.DEBUG) {
                    return true
                }
                return priority > Logger.DEBUG
            }
        })
    }
}