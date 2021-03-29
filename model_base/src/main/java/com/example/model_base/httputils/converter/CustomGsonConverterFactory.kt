package com.example.model_base.httputils.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/29 17:06
 */
class CustomGsonConverterFactory private constructor(private val gson: Gson?) :
    Converter.Factory() {
    init {
        if (gson == null) throw NullPointerException("gson == null")
    }

    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        val adapter = gson!!.getAdapter(TypeToken.get(type!!))
        return CustomGsonResponseBodyConverter(gson!!, adapter)
    }

    override fun requestBodyConverter(type: Type, parameterAnnotations: Array<out Annotation>, methodAnnotations: Array<out Annotation>, retrofit: Retrofit): Converter<*, RequestBody>? {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
    }
    companion object {

        @JvmOverloads
        fun create(gson: Gson = Gson()): CustomGsonConverterFactory {
            return CustomGsonConverterFactory(gson)
        }
    }
}