package com.example.model_base.httputils.intercepter

import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset

/**
 * title：LoggingInterceptor
 * Description：自定义log打印拦截器
 * author：luojiongtian on 2019/9/11 14:07
 */
class LoggingInterceptor : Interceptor {
    private val UTF8 = Charset.forName("UTF-8")
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        //=========发送===========
        val request = chain.request()
        val requestTime = System.currentTimeMillis()//请求发起的时间
        val requestUrl = request.url().uri()

        val requestBody = request.body()
        var body: String? = null
        requestBody?.let {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            var charset: Charset? = UTF8
            val contentType = requestBody.contentType()
            contentType?.let {
                charset = contentType.charset(UTF8)
            }
            body = buffer.readString(charset!!)
        }
        //打印发送信息
        Logger.d("===LoggingInterceptor===发送==requestUrl=${requestUrl}"
                + "\n===LoggingInterceptor===发送==requestHeaders=${request.headers()}"
                + "\n===LoggingInterceptor===发送==requestBody=${body}\n")
//        Logger.d("===LoggingInterceptor===发送==requestConnection="+chain.connection)

        //=========接收===========
        val responseTime = System.currentTimeMillis()//收到响应的时间
        val response = chain.proceed(chain.request())
        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        //        String content = response.body().string();
        val responseBody = response.peekBody((1024 * 1024).toLong())

        val responseUrl = response.request().url().uri()
        /*val responseHeaders = response.headers()
        val delayTime = responseTime - requestTime*/
        //打印接收信息
        Logger.d("=====LoggingInterceptor===接收==responseUrl=$responseUrl"
//                        +"\n=====LoggingInterceptor===接收==responseHeaders=$responseHeaders"
                        +"\n=====LoggingInterceptor===接收==responseBody=${responseBody.string()}\n")
//        Logger.d("=====LoggingInterceptor===接收==delayTime="+delayTime)
//        Logger.d("=====LoggingInterceptor===接收==responseHeaders=$responseHeaders")
//        Logger.d("=====LoggingInterceptor===接收==responseBody=" + responseBody.string())

        return response
    }
}
