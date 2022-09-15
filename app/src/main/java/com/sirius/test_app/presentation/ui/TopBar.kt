package com.sirius.test_app.presentation.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sirius.test_app.R
import com.sirius.test_app.presentation.ui.theme.FabBackground
import com.sirius.test_app.presentation.ui.theme.HeaderGradientEnd
import com.sirius.test_app.presentation.ui.theme.HeaderGradientStart
import com.sirius.test_app.presentation.ui.theme.SiriusAppTheme

@Composable
fun TopBar(
    onBackClick: () -> Unit,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(126.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(HeaderGradientStart, HeaderGradientEnd)
                )
            )
            .padding(start = 24.dp, end = 24.dp, bottom = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        NavigationFAB(
            iconRes = R.drawable.ic_arrow_left,
            contentDescriptionRes = R.string.back_content_desc,
            onCLick = onBackClick
        )
        NavigationFAB(
            iconRes = R.drawable.ic_more_square,
            contentDescriptionRes = R.string.menu_content_desc,
            onCLick = onMenuClick
        )
    }
}

@Composable
private fun NavigationFAB(
    @DrawableRes iconRes: Int,
    @StringRes contentDescriptionRes: Int,
    onCLick: () -> Unit
) {
    FloatingActionButton(
        onClick = onCLick,
        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp),
        backgroundColor = FabBackground,
        contentColor = Color.White
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = stringResource(contentDescriptionRes)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TopBar_Preview() {
    SiriusAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            TopBar(
                onBackClick = { },
                onMenuClick = { }
            )
        }
    }
}
