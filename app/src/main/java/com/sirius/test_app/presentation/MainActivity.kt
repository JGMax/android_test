package com.sirius.test_app.presentation

import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sirius.test_app.R
import com.sirius.test_app.data.DataModel
import com.sirius.test_app.databinding.ActivityMainBinding
import com.sirius.test_app.presentation.mapper.MainPresentationMapper
import com.sirius.test_app.presentation.model.recycler.MainItemReview
import com.sirius.test_app.recycler.RecyclerManager
import com.sirius.test_app.recycler.clicks.clicks
import com.sirius.test_app.recycler.manager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::class.java)

    private val viewModel: MainViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(
            this,
            MainViewModelFactory(DataModel(), MainPresentationMapper())
        ).get(MainViewModel::class.java)
    }

    private val recycler: RecyclerManager by lazy(LazyThreadSafetyMode.NONE) {
        binding.recyclerView.manager()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS)

        recycler.submitList(viewModel.getDataAboutGame())

        lifecycleScope.launchWhenResumed {
            recycler.clicks<MainItemReview>(R.id.avatar_image)
                .onEach { toast(getString(R.string.avatar_click, it.name)) }
                .collect()
        }

        /**
         * Весь контент очевидно не поместится на экране, по макету не понятно,
         * как дожна себя вести кнопка при скролле
         * Добавил ее поверх контента я осознанно
         */
        with(binding) {
            install.setOnClickListener {
                toast(getString(R.string.motivation_click))
            }
            back.setOnClickListener {
                toast(getString(R.string.motivation_click))
                onBackPressed()
            }
            menu.setOnClickListener { toast(getString(R.string.motivation_click)) }
        }
    }

    private fun toast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP, 0, 0)
        toast.show()
    }
}
