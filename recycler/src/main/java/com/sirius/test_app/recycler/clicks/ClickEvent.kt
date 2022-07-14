package com.sirius.test_app.recycler.clicks

import android.view.View
import com.sirius.test_app.recycler.holder.RecyclerViewHolder
import com.sirius.test_app.recycler.item.RecyclerItem
import java.lang.ref.WeakReference

sealed class ClickEvent(val view: WeakReference<View>) {
    internal class HolderClick(
        val holder: WeakReference<RecyclerViewHolder>,
        view: WeakReference<View>
    ) : ClickEvent(view)

    class ItemClick(val item: RecyclerItem, view: WeakReference<View>) : ClickEvent(view)
}
