package io.github.peerapongsam.heropedia.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VD : ViewDataBinding> : AppCompatActivity() {

    private lateinit var binding: VD

    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createViewModel()
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        setUpView(binding)
    }

    open fun createViewModel() {}

    open fun setUpView(binding: VD) {}
}
