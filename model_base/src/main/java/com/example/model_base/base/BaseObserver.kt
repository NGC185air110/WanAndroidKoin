package com.example.model_base.base

import android.widget.Toast
import com.example.model_base.app.BaseApplication
import io.reactivex.observers.DisposableObserver

/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/25 15:04
 */
open class BaseObserver<T> : DisposableObserver<T>() {
    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {
        Toast.makeText(BaseApplication.context, "网络错误", Toast.LENGTH_SHORT).show()
    }

    override fun onComplete() {

    }
}