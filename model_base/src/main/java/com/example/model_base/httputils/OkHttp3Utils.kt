package com.example.model_base.httputils

import com.example.model_base.app.BaseApplication
import com.example.model_base.httputils.cookie.SessionCookieJar
import com.example.model_base.httputils.intercepter.LoggingInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/22 10:06
 */
object OkHttp3Utils {
    private var mOkHttpClient: OkHttpClient? = null

    //设置缓存目录
    private val cacheDirectory = File(BaseApplication.context.applicationContext.cacheDir.absolutePath, "wanandroidkoin")
    private val cache = Cache(cacheDirectory, (10 * 1024 * 1024).toLong())


    val okHttpClient: OkHttpClient
        get() {
            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttpClient.Builder()
                    .cookieJar(SessionCookieJar())
                    .addInterceptor(LoggingInterceptor())
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .cache(cache)
                    .build()
            }
            return mOkHttpClient!!
        }
}