package io.github.peerapongsam.heropedia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VD : ViewDataBinding> : Fragment() {

    abstract val layoutRes: Int

    private lateinit var binding: VD

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (layoutRes != 0) {
            binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
            binding.lifecycleOwner = this
            setUpView(binding)
            return binding.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    open fun setUpView(binding: VD) {
    }
}
