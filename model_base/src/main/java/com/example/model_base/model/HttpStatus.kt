package com.example.model_base.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class HttpStatus : Serializable {
    @SerializedName("errorMsg")
    val errorMsg: String? = null
    @SerializedName("errorCode")
    val errorCode: Int = 0

    val isCodeInvalid: Boolean
        get() = errorCode != 0
}
