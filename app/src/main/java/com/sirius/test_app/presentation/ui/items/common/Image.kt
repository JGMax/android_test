package com.sirius.test_app.presentation.ui.items.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.sirius.test_app.R


@Composable
fun Image(
    link: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = link,
        contentDescription = stringResource(R.string.game_poster_content_desc),
        contentScale = ContentScale.Crop,
        placeholder = rememberAsyncImagePainter(
            ContextCompat.getDrawable(LocalContext.current, R.drawable.placeholder)
        ),
        onError = { println("xxx ${it.result.throwable.localizedMessage}") },
        modifier = modifier
    )
}
