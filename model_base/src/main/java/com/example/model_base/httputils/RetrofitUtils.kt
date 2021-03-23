package com.example.model_base.httputils

import com.example.model_base.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * title：
 * description：Retrofit单例
 * author：dinglicheng on 2021/3/23 9:56
 */
object RetrofitUtils {

    //服务器路径
    val apiServer = BuildConfig.API_URL//请求地址
    private var mRetrofit: Retrofit? = null
    private var mOkHttpClient: OkHttpClient? = null

    val retrofit: Retrofit
        get() {
            if (null == mRetrofit) {
                if (null == mOkHttpClient) {
                    mOkHttpClient = OkHttp3Utils.okHttpClient
                }
                mRetrofit = Retrofit.Builder()
                    .baseUrl("$apiServer/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mOkHttpClient!!)
                    .build()

            }
            return mRetrofit!!
        }
}