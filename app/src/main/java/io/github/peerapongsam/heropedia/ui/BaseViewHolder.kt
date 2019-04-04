package io.github.peerapongsam.heropedia.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.ViewHolder

open class BaseViewHolder<T : Any>(protected val binding: ViewDataBinding) : ViewHolder(binding.root) {

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
