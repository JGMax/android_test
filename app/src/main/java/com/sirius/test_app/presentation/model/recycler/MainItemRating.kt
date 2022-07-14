package com.sirius.test_app.presentation.model.recycler

import com.sirius.test_app.R
import com.sirius.test_app.databinding.ItemRatingBinding
import com.sirius.test_app.recycler.holder.RecyclerViewHolder
import com.sirius.test_app.recycler.holder.getBinding
import com.sirius.test_app.recycler.item.RecyclerItem

data class MainItemRating(
    private val rating: Float,
    private val reviewsCount: String
) : RecyclerItem() {
    override val layoutId: Int = R.layout.item_rating

    override fun bind(holder: RecyclerViewHolder) {
        val binding = holder.getBinding { ItemRatingBinding.bind(it) }
        binding.rating.text = rating.toString()
        binding.ratingBar.rating = rating
        binding.reviewers.run { text = context.getString(R.string.reviews_count, reviewsCount) }
    }
}
