package com.example.model_base.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<BindingClass> : AppCompatActivity(), View.OnClickListener {

    private var innerBinding: BindingClass? = null

    val binding: BindingClass by lazy { innerBinding!! }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val acName = javaClass.simpleName
        val name = acName.substring(0, acName.indexOf("Activity"))
        val bindingClass = classLoader.loadClass("com.example.wanandroidkoin.databinding.Activity${name}Binding")
        innerBinding = bindingClass.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as BindingClass
        lifecycle.addObserver(BaseLifecycle())

        setContentView(layoutId())
        initConfigure()
        initView()
        initListener()
        initData()
    }




    abstract fun layoutId(): Int

    /**
     * 用于实现懒加载，只在当前页面可见时加载
     *   * @param isVisible true  不可见 -> 可见
     * false 可见  -> 不可见
     */
    protected open fun onActivityVisibleChange(isVisible: Boolean) {
    }

    override fun onResume() {
        super.onResume()
        onActivityVisibleChange(true)
    }

    override fun onPause() {
        super.onPause()
        onActivityVisibleChange(false)
    }

    /**
     * 设置点击监听的方法
     * @param view
     */
    fun setOnClick(vararg view: View) {
        for (v in view) {
            v.setOnClickListener(this)
        }
    }


    /**
     * 先于onCreate执行
     */
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

    /**
     * 初始化配置
     */
    private fun initConfigure() {}

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     *初始化 View
     */
    open fun initView() {}

    /**
     * 初始化监听
     */
    abstract fun initListener()
}