package com.sirius.test_app.presentation.ui.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sirius.test_app.presentation.model.recycler.MainItemHeader
import com.sirius.test_app.presentation.ui.items.common.Image
import com.sirius.test_app.presentation.ui.items.common.RatingBar
import com.sirius.test_app.presentation.ui.theme.SiriusAppTheme

@Composable
fun ItemHeader(item: MainItemHeader) {
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            link = item.imageLink,
            modifier = Modifier
                .heightIn(max = 354.dp)
                .fillMaxWidth()
        )
        Surface(
            color = MaterialTheme.colors.background,
            shape = RoundedCornerShape(topStart = 42.dp, topEnd = 42.dp)
        ) {
            Header(
                title = item.title,
                rating = item.rating,
                reviewsCount = item.reviewsCount,
                logoLink = item.logoLink,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 16.dp, top = 24.dp)
            )
        }
    }
}

@Composable
private fun Header(
    title: String,
    rating: Float,
    reviewsCount: String,
    logoLink: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            link = logoLink,
            modifier = Modifier.size(88.dp, 95.dp)
        )
        HeaderTitle(
            title = title,
            rating = rating,
            reviewsCount = reviewsCount,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
private fun HeaderTitle(
    title: String,
    rating: Float,
    reviewsCount: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 7.dp)
        ) {
            RatingBar(
                rating = rating,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = reviewsCount,
                style = MaterialTheme.typography.body2,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemHeader_Preview() {
    SiriusAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            ItemHeader(
                item = MainItemHeader(
                    title = "Title",
                    rating = 3f,
                    reviewsCount = "1M",
                    logoLink = "",
                    imageLink = ""
                )
            )
        }
    }
}
