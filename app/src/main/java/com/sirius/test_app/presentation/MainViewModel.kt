package com.sirius.test_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sirius.test_app.data.DataModel
import com.sirius.test_app.presentation.mapper.MainPresentationMapper
import com.sirius.test_app.recycler.item.RecyclerItem

class MainViewModel(
    private val dataModel: DataModel,
    private val mapper: MainPresentationMapper
) : ViewModel() {

    fun getDataAboutGame(): List<RecyclerItem> = mapper.map(dataModel)
}

class MainViewModelFactory(
    private val dataModel: DataModel,
    private val mapper: MainPresentationMapper
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(dataModel, mapper) as T
    }
}
