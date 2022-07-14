package com.sirius.test_app.recycler.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sirius.test_app.recycler.clicks.ClicksManager
import com.sirius.test_app.recycler.clicks.ClicksOwner
import com.sirius.test_app.recycler.holder.RecyclerViewHolder
import com.sirius.test_app.recycler.holder.ViewTypeInitializer
import com.sirius.test_app.recycler.item.RecyclerItem
import com.sirius.test_app.recycler.list.DifferListOwner
import com.sirius.test_app.recycler.list.ListOwner
import com.sirius.test_app.recycler.list.SimpleListOwner
import com.sirius.test_app.recycler.util.inflate

class RecyclerAdapter(
    diffCallback: DiffUtil.ItemCallback<RecyclerItem>?
) : RecyclerView.Adapter<RecyclerViewHolder>(), ClicksOwner {

    override val clicksManager = ClicksManager(this)
    private val viewTypes: MutableMap<Int, () -> ViewTypeInitializer> = mutableMapOf()

    private val listOwner: ListOwner<RecyclerItem> by lazy {
        diffCallback?.let { DifferListOwner(this, it) } ?: SimpleListOwner(this)
    }

    fun submitList(list: List<RecyclerItem>, onCommit: () -> Unit = {}) {
        listOwner.submitList(list, onCommit)
    }

    override fun getItemViewType(position: Int): Int {
        val item = listOwner[position]
        if (item.viewTypeFactory != null && item.layoutId !in viewTypes) {
            viewTypes[item.layoutId] = item.viewTypeFactory
        }
        return item.layoutId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            parent.inflate(viewType),
            viewTypes[viewType]?.invoke(),
            clicksManager
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(listOwner[position])
    }

    override fun onBindViewHolder(
        holder: RecyclerViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.bind(listOwner[position], payloads)
        }
    }

    override fun getItemCount(): Int = listOwner.size

    operator fun get(position: Int) = listOwner[position]
}
