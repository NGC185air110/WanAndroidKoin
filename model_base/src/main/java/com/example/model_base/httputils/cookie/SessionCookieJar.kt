package com.example.model_base.httputils.cookie

import com.example.model_base.app.BaseApplication
import com.tencent.mmkv.MMKV
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.util.ArrayList


/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/22 13:21
 */
class SessionCookieJar : CookieJar {
    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        val kv:MMKV? = MMKV.defaultMMKV()
        if (cookies.isNotEmpty()){
            for (cookie in cookies){
                if(cookie.value().isNotEmpty()){
                    kv?.encode(cookie.name(),cookie.value())
                    BaseApplication.instance!!.sessionId = BaseApplication.instance!!.sessionId + cookie.value()
                }
            }
        }
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        val kv:MMKV? = MMKV.defaultMMKV()
        val cookies = ArrayList<Cookie>()
        val sessionId =kv?.getString("loginUserName_wanandroid_com","")
        val cookie = Cookie.Builder()
            .hostOnlyDomain(url.host())
            .name("loginUserName_wanandroid_com")
            .value(sessionId)
            .build()
        cookies.add(cookie)

        val sessionId1 =kv?.getString("token_pass_wanandroid_com","")
        val cookie1 = Cookie.Builder()
            .hostOnlyDomain(url.host())
            .name("token_pass_wanandroid_com")
            .value(sessionId1)
            .build()
        cookies.add(cookie1)
        return  cookies
    }
}