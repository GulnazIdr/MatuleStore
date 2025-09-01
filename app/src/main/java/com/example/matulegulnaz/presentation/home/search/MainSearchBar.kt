package com.example.matulegulnaz.presentation.home.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matulegulnaz.R
import com.example.matulegulnaz.presentation.components.CustomRoundedButton
import com.example.matulegulnaz.ui.theme.bluePrimaryColor

@Composable
fun MainSearchBar(
    onSettings: () -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SearchField(
            placeholder = "Looking for shoes",
            modifier = Modifier.weight(1f),
            onSearch = {onSearch()}
        )

        Spacer(modifier = Modifier.width(14.dp))

        CustomRoundedButton(
            onAction = onSettings,
            painterResource = R.drawable.filter,
            backgroundColor = bluePrimaryColor
        )
    }

}

@Preview
@Composable
private fun SearchBarPreview() {
    MainSearchBar(
        onSettings = {},
        {}
    )
}