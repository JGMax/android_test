package com.sirius.test_app.presentation.ui.items

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import android.view.ContextThemeWrapper
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.google.android.material.chip.Chip
import com.sirius.test_app.R
import com.sirius.test_app.presentation.model.recycler.MainItemDescription
import com.sirius.test_app.presentation.ui.theme.SiriusAppTheme

@Composable
internal fun ItemDescription(item: MainItemDescription) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
    ) {
        DescriptionTags(tags = item.tags, modifier = Modifier.fillMaxWidth())
        DescriptionText(text = item.description, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun DescriptionTags(
    tags: List<String>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier.padding(bottom = 24.dp),
        mainAxisSpacing = 8.dp
    ) {
        tags.forEach { tag ->
            AndroidView(
                factory = { context -> Chip(ContextThemeWrapper(context, R.style.TestApp_Chip)) }
            ) {
                it.isClickable = false
                it.text = tag
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    it.setTextColor(it.context.getColor(R.color.chip_text))
                } else {
                    it.setTextColor(ContextCompat.getColor(it.context, R.color.chip_text))
                }
            }
        }
    }
}

@Composable
private fun DescriptionText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        modifier = modifier
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ItemDescription_Preview() {
    SiriusAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            ItemDescription(
                MainItemDescription(
                    tags = listOf("xxx", "xxx"),
                    description = "Hello world"
                )
            )
        }
    }
}
