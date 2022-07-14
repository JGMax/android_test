package com.sirius.test_app.presentation.model.recycler

import coil.load
import com.sirius.test_app.R
import com.sirius.test_app.databinding.ItemReviewBinding
import com.sirius.test_app.recycler.holder.RecyclerViewHolder
import com.sirius.test_app.recycler.holder.ViewTypeInitializer
import com.sirius.test_app.recycler.holder.getBinding
import com.sirius.test_app.recycler.item.RecyclerItem

data class MainItemReview(
    private val avatarLink: String,
    val name: String,
    private val date: String,
    private val review: String
) : RecyclerItem({ ReviewInitializer() }) {
    override val layoutId: Int = R.layout.item_review

    override fun bind(holder: RecyclerViewHolder) {
        val binding = holder.getBinding { ItemReviewBinding.bind(it) }

        binding.name.text = name
        binding.date.text = date
        binding.review.text = review
        binding.avatarImage.load(avatarLink) {
            crossfade(true)
            placeholder(R.drawable.placeholder)
        }
    }
}

private class ReviewInitializer : ViewTypeInitializer() {

    override fun init(holder: RecyclerViewHolder) {
        holder.binding(ItemReviewBinding.bind(holder.itemView)) {
            clicks(avatarImage, holder)
        }
    }
}
