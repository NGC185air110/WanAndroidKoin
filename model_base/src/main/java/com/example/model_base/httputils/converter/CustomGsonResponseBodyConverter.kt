package com.example.model_base.httputils.converter

import com.example.model_base.httputils.converter.error.ApiException
import com.example.model_base.model.HttpStatus
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/29 17:17
 */
internal class CustomGsonResponseBodyConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T? {
        val response = value.string()

        val httpStatus = gson.fromJson(response,HttpStatus::class.java)
        if (httpStatus.isCodeInvalid){
            value.close()
            throw ApiException(httpStatus.errorCode, httpStatus.errorMsg!!)
        }
        val contentType = value.contentType()
        val charset = if (contentType != null) contentType.charset(StandardCharsets.UTF_8) else StandardCharsets.UTF_8
        val inputStream = ByteArrayInputStream(response.toByteArray())
        val reader = InputStreamReader(inputStream, charset)
        val jsonReader = gson.newJsonReader(reader)

        try {
            return adapter.read(jsonReader)
        } finally {
            value.close()
        }
    }
}