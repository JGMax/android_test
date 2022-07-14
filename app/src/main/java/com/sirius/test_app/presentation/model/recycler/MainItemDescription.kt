package com.sirius.test_app.presentation.model.recycler

import android.os.Build
import android.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.sirius.test_app.R
import com.sirius.test_app.databinding.ItemDecriptionBinding
import com.sirius.test_app.recycler.holder.RecyclerViewHolder
import com.sirius.test_app.recycler.holder.getBinding
import com.sirius.test_app.recycler.item.RecyclerItem

data class MainItemDescription(
    private val tags: List<String>,
    private val description: String
) : RecyclerItem() {
    override val layoutId: Int = R.layout.item_decription

    override fun bind(holder: RecyclerViewHolder) {
        val binding = holder.getBinding { ItemDecriptionBinding.bind(it) }

        tags.forEach {
            val chip = Chip(ContextThemeWrapper(binding.root.context, R.style.TestApp_Chip)).apply {
                text = it
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextColor(context.getColor(R.color.chip_text))
                } else {
                    setTextColor(ContextCompat.getColor(context, R.color.chip_text))
                }
            }
            binding.tags.addView(chip)
        }
        binding.description.text = description
    }
}
