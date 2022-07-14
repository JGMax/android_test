package com.sirius.test_app.recycler.holder

import android.view.View
import androidx.viewbinding.ViewBinding
import com.sirius.test_app.recycler.clicks.ClicksManager

@Suppress("UNCHECKED_CAST")
abstract class ViewTypeInitializer {
    private lateinit var clicksManager: ClicksManager

    protected abstract fun init(holder: RecyclerViewHolder)

    internal fun init(holder: RecyclerViewHolder, clicksManager: ClicksManager) {
        this.clicksManager = clicksManager
        init(holder)
    }

    protected fun <B : ViewBinding> RecyclerViewHolder.binding(
        binding: B,
        block: B.() -> Unit = {}
    ) {
        this.binding = binding
        binding.block()
    }

    protected fun clicks(view: View, viewHolder: RecyclerViewHolder) {
        clicksManager.clicks(view, viewHolder)
    }
}
