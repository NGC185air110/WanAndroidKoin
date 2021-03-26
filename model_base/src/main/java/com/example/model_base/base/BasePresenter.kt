package com.example.model_base.base

import com.example.model_base.api.ApiService
import com.example.model_base.httputils.OkHttp3Utils
import com.example.model_base.httputils.RetrofitUtils
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * title：
 * description：
 * author：dinglicheng on 2021/3/24 14:46
 */
abstract class BasePresenter<V> {

    protected var mApi: ApiService = RetrofitUtils.retrofit.create(ApiService::class.java)
    private var mView: V? = null

    var compositeDisposable: CompositeDisposable? = null

    constructor(view: V) {
        attachView(view)
    }

    private fun attachView(view: V) {
        mView = view
    }

    fun detachView() {
        mView = null
        onUnsubscribe()
    }

    fun <T> addSubscription(observable: Observable<T>, disposable: BaseObserver<T>) {
        if (compositeDisposable == null)
            compositeDisposable = CompositeDisposable()
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposable)
        compositeDisposable!!.add(disposable)
    }

    //RXjava取消注册，以避免内存泄露
    fun onUnsubscribe() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
    }

    open fun getJsonRequestBody(hashMap: Map<String, String>): RequestBody? {
        return RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            Gson().toJson(hashMap)
        )
    }


    open fun getJsonRequestBody(obj: Any): RequestBody? {
        return RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            Gson().toJson(obj)
        )
    }
}