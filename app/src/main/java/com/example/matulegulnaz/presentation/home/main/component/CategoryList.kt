package com.example.matulegulnaz.presentation.home.main.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matulegulnaz.domain.product.CategoryInfo
import com.example.matulegulnaz.presentation.components.NavigationButton

@Composable
fun CategoryList(
    onCategory: (id: Int) -> Unit,
    listOfButtons: List<CategoryInfo>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        LazyRow {
            items(listOfButtons.size) { button ->
                NavigationButton(
                    text = listOfButtons[button].name,
                    onAction = {onCategory(button)},
                    isCategory = true,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .height(40.dp)
                        .width(108.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun CategoryListPreview() {
}