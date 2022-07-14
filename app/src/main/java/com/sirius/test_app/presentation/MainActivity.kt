package com.sirius.test_app.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sirius.test_app.R
import com.sirius.test_app.data.DataModel
import com.sirius.test_app.databinding.ActivityMainBinding
import com.sirius.test_app.presentation.mapper.MainPresentationMapper
import com.sirius.test_app.recycler.RecyclerManager
import com.sirius.test_app.recycler.manager

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::class.java)

    private val viewModel: MainViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(
            this,
            MainViewModelFactory(DataModel(), MainPresentationMapper()) // TODO Replace with di
        ).get(MainViewModel::class.java)
    }

    private val recycler: RecyclerManager by lazy(LazyThreadSafetyMode.NONE) {
        binding.recyclerView.manager()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler.submitList(viewModel.getDataAboutGame())
    }
}
