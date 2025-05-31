package com.ita.poppop.base

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T: ViewDataBinding>(@LayoutRes val layoutRes: Int)
    : AppCompatActivity() {
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // 인플레이션 방식 변경
        binding = DataBindingUtil.inflate(
            layoutInflater,
            layoutRes,
            null,
            false
        )
        setContentView(binding.root)
        // LiveData를 사용하기 위한 lifecycleOwner 지정
        binding.lifecycleOwner = this@BaseActivity
    }
}