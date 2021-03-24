package com.example.model_base.api

import com.example.model_base.data.ConstantUrl
import com.example.model_base.model.ResultResponse
import com.example.model_base.model.login.UserLoginBase
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/24 14:48
 */
interface ApiService {
    /**
     * 用户登录
     */
    @POST(ConstantUrl.POSTUSERTLOGIN)
    fun userLogin(@Body requestBody: RequestBody?): Observable<ResultResponse<UserLoginBase>>
}