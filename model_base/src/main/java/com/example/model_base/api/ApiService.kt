package com.example.model_base.api

import com.example.model_base.data.ConstantUrl
import com.example.model_base.model.ResultResponse
import com.example.model_base.model.login.UserLoginBase
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/24 14:48
 */
interface ApiService {
    /**
     * 用户登录
     */
    @FormUrlEncoded
    @POST(ConstantUrl.POSTUSERTLOGIN)
    fun userLogin(@FieldMap map: Map<String, String>): Observable<ResultResponse<UserLoginBase>>

    @GET("${ConstantUrl.GETFAVORITES}/{path}")
    fun getFavorites(@Path("path") url: String): Observable<ResultResponse<Any>>
}