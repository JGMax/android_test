package com.sirius.test_app.presentation.ui.items

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sirius.test_app.R
import com.sirius.test_app.presentation.model.recycler.MainItemRating
import com.sirius.test_app.presentation.ui.items.common.RatingBar
import com.sirius.test_app.presentation.ui.theme.SiriusAppTheme
import com.sirius.test_app.presentation.ui.theme.TextRegular

@Composable
fun ItemRating(item: MainItemRating) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 8.dp, top = 24.dp)
    ) {
        RatingTitle(
            modifier = Modifier
                .padding(bottom = 12.dp)
        )
        RatingContent(
            ratingValue = item.rating,
            reviewsCount = item.reviewsCount,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun RatingTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.review_and_ratings_header),
        style = MaterialTheme.typography.subtitle1,
        modifier = modifier
    )
}

@Composable
private fun RatingContent(ratingValue: Float, reviewsCount: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RatingValue(
            ratingValue = ratingValue,
            modifier = Modifier.padding(end = 16.dp)
        )
        RatingStats(
            ratingValue = ratingValue,
            reviewsCount = reviewsCount,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun RatingValue(ratingValue: Float, modifier: Modifier = Modifier) {
    Text(
        text = ratingValue.toString(),
        style = MaterialTheme.typography.h5.copy(fontSize = 48.sp),
        modifier = modifier
    )
}

@Composable
private fun RatingStats(ratingValue: Float, reviewsCount: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        RatingBar(rating = ratingValue)
        Text(
            text = stringResource(R.string.reviews_count, reviewsCount),
            style = MaterialTheme.typography.body2.copy(color = TextRegular)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemRating_Preview() {
    SiriusAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            ItemRating(
                item = MainItemRating(
                    rating = 3f,
                    reviewsCount = "100500M",
                )
            )
        }
    }
}
