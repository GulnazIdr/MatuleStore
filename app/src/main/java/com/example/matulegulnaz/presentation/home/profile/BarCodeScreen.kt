package com.example.matulegulnaz.presentation.home.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matulegulnaz.R
import com.example.matulegulnaz.presentation.authorization.signIn.ModalWindow
import com.example.matulegulnaz.presentation.components.CommonScaffold

@Composable
fun BarCodeScreen(
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    CommonScaffold(snackbarHostState) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp).align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(R.drawable.barcode_example),
                    contentDescription = "barcode image",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                        setToScale(2.5f, 2.5f, 2.5f, 1f)
                    })
                )
            }
        }
    }
}

@Preview
@Composable
private fun BarCodeScreenPreview() {
    BarCodeScreen()
}