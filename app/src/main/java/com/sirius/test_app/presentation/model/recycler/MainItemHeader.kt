package com.sirius.test_app.presentation.model.recycler

import coil.load
import com.sirius.test_app.R
import com.sirius.test_app.databinding.ItemHeaderBinding
import com.sirius.test_app.presentation.model.ListItem
import com.sirius.test_app.recycler.holder.RecyclerViewHolder
import com.sirius.test_app.recycler.holder.getBinding
import com.sirius.test_app.recycler.item.RecyclerItem

data class MainItemHeader(
    val title: String,
    val rating: Float,
    val reviewsCount: String,
    val logoLink: String,
    val imageLink: String
) : RecyclerItem(), ListItem {
    override val layoutId: Int = R.layout.item_header

    override fun bind(holder: RecyclerViewHolder) {
        val binding = holder.getBinding { ItemHeaderBinding.bind(it) }

        with(binding) {
            gameTitle.text = title
            ratingBar.rating = rating
            reviewsNum.text = reviewsCount
            logo.load(logoLink) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
            }
            image.load(imageLink) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
            }
        }
    }
}
