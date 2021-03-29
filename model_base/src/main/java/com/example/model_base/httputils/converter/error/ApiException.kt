package com.example.model_base.httputils.converter.error

/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/29 17:53
 */
class ApiException(private val mErrorCode: Int, errorMessage: String) : RuntimeException(errorMessage) {
    val isNetWorkError : Boolean
        get() = mErrorCode == -1
}