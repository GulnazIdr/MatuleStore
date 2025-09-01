package com.example.matulegulnaz.presentation.home.main.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.matulegulnaz.ui.theme.bluePrimaryColor
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun CategoryHeader(
    onSeeAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .zIndex(0f)
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Select category",
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.W600,
            fontSize = 16.sp
        )

        Text(
            text = "See all",
            modifier = Modifier.clickable(
                onClick = onSeeAllClick
            ),
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            color = bluePrimaryColor
        )
    }

    Spacer(modifier = Modifier.height(16.dp))
}

@Preview
@Composable
private fun CategoryHeaderPreview() {
    CategoryHeader({})
}