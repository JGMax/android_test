package com.sirius.test_app.recycler.clicks

import kotlinx.coroutines.flow.Flow
import com.sirius.test_app.recycler.item.RecyclerItem

interface ClicksOwner {
    val clicksManager: ClicksManager
}

inline fun <reified T : RecyclerItem> ClicksOwner.clicks(): Flow<ClickEvent.ItemClick> {
    return clicksManager.clicks<T>()
}

inline fun <reified T : RecyclerItem> ClicksOwner.clicks(viewId: Int): Flow<T> {
    return clicksManager.clicks(viewId)
}
