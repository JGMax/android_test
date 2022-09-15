package com.sirius.test_app.presentation.ui.items.common

import android.view.LayoutInflater
import android.widget.RatingBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.sirius.test_app.R

@Composable
fun RatingBar(
    rating: Float,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { LayoutInflater.from(it).inflate(R.layout.rating, null) as RatingBar },
        modifier = modifier
    ) { it.rating = rating }
}
