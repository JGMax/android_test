package com.sirius.test_app.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sirius.test_app.R
import com.sirius.test_app.presentation.model.ListItem
import com.sirius.test_app.presentation.model.recycler.MainItemDescription
import com.sirius.test_app.presentation.model.recycler.MainItemHeader
import com.sirius.test_app.presentation.model.recycler.MainItemRating
import com.sirius.test_app.presentation.model.recycler.MainItemReview
import com.sirius.test_app.presentation.ui.items.common.Item
import com.sirius.test_app.presentation.ui.theme.ButtonPrimary
import com.sirius.test_app.presentation.ui.theme.Divider
import com.sirius.test_app.presentation.ui.theme.SiriusAppTheme

@Composable
fun MainScreen(
    items: List<ListItem>,
    onItemClick: (ListItem) -> Unit,
    onMenuClick: () -> Unit,
    onBackClick: () -> Unit,
    onInstallClick: () -> Unit
) {
    Box {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(items, contentType = { it }) {
                Item(it, onItemClick)
                when (it) {
                    is MainItemRating -> MainScreenDivider()
                }
            }

            item { Spacer(modifier = Modifier.height(122.dp)) }
        }

        TopBar(
            onBackClick = onBackClick,
            onMenuClick = onMenuClick,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        InstallButton(
            onClick = onInstallClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 36.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun MainScreenDivider() {
    Divider(
        color = Divider,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    )
}

@Composable
private fun InstallButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier.heightIn(min = 64.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = ButtonPrimary)
    ) {
        Text(
            text = stringResource(R.string.install)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreen_Preview() {
    SiriusAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            MainScreen(
                onItemClick = {},
                onBackClick = {},
                onMenuClick = {},
                onInstallClick = {},
                items = listOf(
                    MainItemHeader(
                        title = "Title",
                        rating = 3f,
                        reviewsCount = "1M",
                        logoLink = "",
                        imageLink = ""
                    ),
                    MainItemDescription(
                        tags = listOf("xxx", "xxx"),
                        description = "Hello world"
                    ),
                    MainItemRating(
                        rating = 3f,
                        reviewsCount = "100500M",
                    ),
                    MainItemReview(
                        avatarLink = "",
                        name = "Name",
                        date = "10.05.2020",
                        review = "Review"
                    )
                )
            )
        }
    }
}
