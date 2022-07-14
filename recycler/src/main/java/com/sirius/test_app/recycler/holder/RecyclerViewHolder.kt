package com.sirius.test_app.recycler.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sirius.test_app.recycler.clicks.ClicksManager
import com.sirius.test_app.recycler.item.RecyclerItem

class RecyclerViewHolder internal constructor(
    view: View,
    viewType: ViewTypeInitializer?,
    clicksManager: ClicksManager
) : RecyclerView.ViewHolder(view) {

    internal var binding: ViewBinding? = null

    init {
        viewType?.init(this, clicksManager)
    }

    fun bind(item: RecyclerItem) {
        item.bind(this)
    }

    fun bind(item: RecyclerItem, payloads: MutableList<Any>) {
        item.bind(this, payloads)
    }
}

@Suppress("UNCHECKED_CAST")
fun <T : ViewBinding> RecyclerViewHolder.getBinding(
    bindingFactory: (View) -> T
): T {
    if(binding == null) {
        binding = bindingFactory(itemView)
    }
    return binding as T
}
