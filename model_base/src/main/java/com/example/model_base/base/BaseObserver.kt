package com.example.model_base.base

import android.widget.Toast
import com.example.model_base.app.BaseApplication
import com.example.model_base.httputils.converter.error.ApiException
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
        when (e) {
            is ApiException -> {
                when {
                    e.isNetWorkError -> {
                        Toast.makeText(BaseApplication.context, "网络开小差请重试", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else -> {
                Toast.makeText(BaseApplication.context, "网络错误", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onComplete() {
        dispose()
    }
}