package com.example.matulegulnaz.presentation.home.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.R
import com.example.matulegulnaz.ui.theme.lightGrey
import com.example.matulegulnaz.ui.theme.poppinsFamily

@Composable
fun SearchField(
    text: String = "",
    onValueChanged: (String) -> Unit = {},
    keyboardActions: KeyboardActions = KeyboardActions(onSearch = {}),
    placeholder: String,
    onSearch: () -> Unit = {},
    onSearchIcon: () -> Unit = {},
    isSearchScreen: Boolean = false,
    onMicro: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(52.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .clickable(onClick = { onSearch() })
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "search icon",
                tint = lightGrey,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .clickable(onClick = { onSearchIcon() })
            )

            if(isSearchScreen) {
//                if(text.isEmpty())
//                    Text(
//                        text = placeholder,
//                        fontFamily = poppinsFamily,
//                        fontWeight = FontWeight.W500,
//                        fontSize = 12.sp,
//                        lineHeight = 20.sp,
//                        color = lightGrey
//                    )
//                else
                    BasicTextField(
                        value = text,
                        onValueChange = onValueChanged,
                        textStyle = TextStyle(
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.W500,
                            fontSize = 12.sp,
                            lineHeight = 20.sp,
                            color = lightGrey
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = keyboardActions
                    )

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    VerticalDivider(
                        color = lightGrey,
                        modifier = Modifier
                            .width(1.dp)
                            .height(24.dp)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.micro),
                        contentDescription = "micro icon",
                        tint = lightGrey,
                        modifier = Modifier
                            .scale(2.5f)
                            .padding(start = 15.dp)
                            .clickable(onClick = { onMicro() })
                    )
                }
            }else
                Text(
                    text = placeholder,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    color = lightGrey
                )
        }
    }
}

@Preview
@Composable
private fun SearchFieldPreview() {
    SearchField(
        placeholder = "Looking for shoes",
        onSearch = {}
    )
}