package com.sirius.test_app.recycler.item

import com.sirius.test_app.recycler.holder.RecyclerViewHolder
import com.sirius.test_app.recycler.holder.ViewTypeInitializer

abstract class RecyclerItem(val viewTypeFactory: (() -> ViewTypeInitializer)? = null) {
    abstract val layoutId: Int
    open val itemId: String = this::class.java.canonicalName ?: ""

    open fun bind(holder: RecyclerViewHolder) = Unit
    open fun bind(holder: RecyclerViewHolder, payloads: MutableList<Any>) = Unit
}
