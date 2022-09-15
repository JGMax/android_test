package com.sirius.test_app.presentation.ui.items.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sirius.test_app.presentation.model.ListItem
import com.sirius.test_app.presentation.model.recycler.MainItemDescription
import com.sirius.test_app.presentation.model.recycler.MainItemHeader
import com.sirius.test_app.presentation.model.recycler.MainItemRating
import com.sirius.test_app.presentation.model.recycler.MainItemReview
import com.sirius.test_app.presentation.ui.items.ItemDescription
import com.sirius.test_app.presentation.ui.items.ItemHeader
import com.sirius.test_app.presentation.ui.items.ItemRating
import com.sirius.test_app.presentation.ui.items.ItemReview

@Composable
fun Item(item: ListItem, onClick: (ListItem) -> Unit) {
    when (item) {
        is MainItemHeader -> ItemHeader(item)
        is MainItemDescription -> ItemDescription(item)
        is MainItemRating -> ItemRating(item)
        is MainItemReview -> ItemReview(item, onClick)
    }
}


@Preview(showBackground = true)
@Composable
private fun Item_Preview() {
    Item(
        item = MainItemDescription(listOf("Tag", "Tag"), "Description"),
        onClick = {}
    )
}
