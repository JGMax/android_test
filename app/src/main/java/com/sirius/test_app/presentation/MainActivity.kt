package com.sirius.test_app.presentation

import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModelProvider
import com.sirius.test_app.R
import com.sirius.test_app.data.DataModel
import com.sirius.test_app.presentation.mapper.MainPresentationMapper
import com.sirius.test_app.presentation.model.ListItem
import com.sirius.test_app.presentation.model.recycler.MainItemReview
import com.sirius.test_app.presentation.ui.MainScreen
import com.sirius.test_app.presentation.ui.theme.SiriusAppTheme


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(
            this,
            MainViewModelFactory(DataModel(), MainPresentationMapper())
        )[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SiriusAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(
                        items = viewModel.getDataAboutGame(),
                        onItemClick = ::onItemClick,
                        onBackClick = ::onBackClick,
                        onMenuClick = ::onMenuClick,
                        onInstallClick = ::onInstallClick
                    )
                }
            }
        }

        window.setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS)
    }

    private fun onItemClick(item: ListItem) {
        when (item) {
            is MainItemReview -> toast(getString(R.string.avatar_click, item.name))
        }
    }

    private fun onBackClick() {
        toast(getString(R.string.motivation_click))
        onBackPressedDispatcher.onBackPressed()
    }

    private fun onMenuClick() {
        toast(getString(R.string.motivation_click))
    }

    private fun onInstallClick() {
        toast(getString(R.string.motivation_click))
    }

    private fun toast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP, 0, 0)
        toast.show()
    }
}
