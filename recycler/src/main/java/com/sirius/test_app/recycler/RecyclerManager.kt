package com.sirius.test_app.recycler

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sirius.test_app.recycler.adapter.RecyclerAdapter
import com.sirius.test_app.recycler.clicks.ClicksOwner
import com.sirius.test_app.recycler.diff_util.DefaultDiffCallback
import com.sirius.test_app.recycler.item.RecyclerItem

class RecyclerManager internal constructor(
    private val adapter: RecyclerAdapter
) : ClicksOwner by adapter {

    val itemCount: Int
        get() = adapter.itemCount

    fun submitList(items: List<RecyclerItem>, onCommit: () -> Unit = {}) {
        adapter.submitList(items, onCommit)
    }
}

class RecyclerManagerBuilder internal constructor(private var recyclerView: RecyclerView?) {
    private var diffCallback: DiffUtil.ItemCallback<RecyclerItem>? = null
    private var layoutManager: RecyclerView.LayoutManager =
        LinearLayoutManager(recyclerView?.context)
    private var adapter: RecyclerAdapter? = null
    private var decorations: Array<out RecyclerView.ItemDecoration> = emptyArray()

    fun diffCallback(
        diffCallback: DiffUtil.ItemCallback<RecyclerItem> = DefaultDiffCallback()
    ): RecyclerManagerBuilder {
        this.diffCallback = diffCallback
        return this
    }

    fun layoutManager(layoutManager: RecyclerView.LayoutManager): RecyclerManagerBuilder {
        this.layoutManager = layoutManager
        return this
    }

    fun adapter(adapter: RecyclerAdapter): RecyclerManagerBuilder {
        this.adapter = adapter
        return this
    }

    fun decorations(vararg decorations: RecyclerView.ItemDecoration): RecyclerManagerBuilder {
        this.decorations = decorations
        return this
    }

    fun build(): RecyclerManager {
        val adapter = adapter ?: RecyclerAdapter(diffCallback)
        recyclerView?.let {
            it.layoutManager = layoutManager
            it.adapter = adapter
            decorations.forEach(it::addItemDecoration)
        }
        val manager = RecyclerManager(adapter)
        recyclerView = null
        return manager
    }
}

fun RecyclerView.managerBuilder(): RecyclerManagerBuilder {
    return RecyclerManagerBuilder(this)
}

fun RecyclerView.manager(): RecyclerManager = managerBuilder().build()

fun RecyclerView.manager(builder: RecyclerManagerBuilder.() -> Unit): RecyclerManager {
    return managerBuilder().apply(builder).build()
}
