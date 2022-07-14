package com.sirius.test_app.recycler.diff_util

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.sirius.test_app.recycler.item.RecyclerItem

class DefaultDiffCallback: DiffUtil.ItemCallback<RecyclerItem>() {

    override fun areItemsTheSame(
        oldItem: RecyclerItem,
        newItem: RecyclerItem
    ): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: RecyclerItem,
        newItem: RecyclerItem
    ): Boolean {
        return oldItem == newItem
    }
}
