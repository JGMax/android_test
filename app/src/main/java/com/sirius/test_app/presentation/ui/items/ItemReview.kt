package com.sirius.test_app.presentation.ui.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sirius.test_app.presentation.model.ListItem
import com.sirius.test_app.presentation.model.recycler.MainItemReview
import com.sirius.test_app.presentation.ui.items.common.Image
import com.sirius.test_app.presentation.ui.theme.SiriusAppTheme
import com.sirius.test_app.presentation.ui.theme.TextDate

@Composable
fun ItemReview(item: MainItemReview, onClick: (ListItem) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 12.dp)
    ) {
        ReviewHeader(
            avatarLink = item.avatarLink,
            name = item.name,
            date = item.date,
            onAvatarClick = { onClick(item) }
        )
        ReviewText(
            text = item.review,
            modifier = Modifier.fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}

@Composable
private fun ReviewHeader(
    avatarLink: String,
    name: String,
    date: String,
    onAvatarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Image(
            link = avatarLink,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .clickable(onClick = onAvatarClick)
        )
        ReviewAuthor(
            name = name,
            date = date,
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp)
        )
    }
}

@Composable
private fun ReviewAuthor(name: String, date: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.subtitle1,
        )
        Text(
            text = date,
            style = MaterialTheme.typography.body2.copy(color = TextDate)
        )
    }
}

@Composable
private fun ReviewText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.body1
    )
}

@Preview(showBackground = true)
@Composable
private fun ItemReview_Preview() {
    SiriusAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            ItemReview(
                item = MainItemReview(
                    avatarLink = "",
                    name = "Name",
                    date = "10.05.2020",
                    review = "Review"
                ),
                onClick = {}
            )
        }
    }
}
