package com.example.model_base.model

import java.io.Serializable

class ResultResponse<T> : Serializable {

    var errorMsg: String? = null
    var errorCode: Int = 0
    var data: T? = null

    constructor(errorMsg: String, errorCode: Int, data: T) {
        this.errorMsg = errorMsg
        this.errorCode = errorCode
        this.data = data
    }
}
