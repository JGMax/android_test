package com.sirius.test_app.recycler.clicks

import android.view.View
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapNotNull
import com.sirius.test_app.recycler.adapter.RecyclerAdapter
import com.sirius.test_app.recycler.clicks.ClickEvent.HolderClick
import com.sirius.test_app.recycler.clicks.ClickEvent.ItemClick
import com.sirius.test_app.recycler.holder.RecyclerViewHolder
import com.sirius.test_app.recycler.item.RecyclerItem
import com.sirius.test_app.recycler.util.weak

class ClicksManager internal constructor(
    private val adapter: RecyclerAdapter
) {

    private val _clicks = MutableSharedFlow<ClickEvent>(extraBufferCapacity = 100)

    val clicks = _clicks.mapNotNull(::mapToItemClick)

    private fun mapToItemClick(clickEvent: ClickEvent): ItemClick? {
        return when (clickEvent) {
            is HolderClick -> {
                val position = clickEvent.holder.get()?.absoluteAdapterPosition ?: NO_POSITION
                if (position == NO_POSITION) return null

                ItemClick(adapter[position], clickEvent.view)
            }
            is ItemClick -> clickEvent
        }
    }

    inline fun <reified T : RecyclerItem> clicks(): Flow<ItemClick> {
        return clicks.filter { it.item is T }
    }

    inline fun <reified T : RecyclerItem> clicks(viewId: Int): Flow<T> {
        return clicks
            .filter { it.view.get()?.id == viewId }
            .mapNotNull { it.item as? T }
    }

    internal fun clicks(view: View, viewHolder: RecyclerViewHolder) {
        val viewHolderWeak = viewHolder.weak()

        view.setOnClickListener { v ->
            if (viewHolderWeak.get() == null) {
                v.setOnClickListener(null)
            } else {
                _clicks.tryEmit(HolderClick(viewHolderWeak, v.weak()))
            }
        }
    }
}
