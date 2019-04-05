package io.github.peerapongsam.heropedia.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.ViewHolder

open class BaseViewHolder<T : Any, VD : ViewDataBinding>(protected val binding: VD) : ViewHolder(binding.root) {

    constructor(parent: ViewGroup, layoutRes: Int) : this(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutRes,
            parent,
            false
        )
    )

    open fun bindValue(item: T) {
    }
}
