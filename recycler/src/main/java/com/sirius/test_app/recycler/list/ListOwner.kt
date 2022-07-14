package com.sirius.test_app.recycler.list

internal interface ListOwner<T> {
    val size: Int

    fun submitList(list: List<T>, onCommit: () -> Unit = {})
    operator fun get(position: Int): T
}
