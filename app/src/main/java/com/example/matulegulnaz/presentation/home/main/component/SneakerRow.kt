package com.example.matulegulnaz.presentation.home.main.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.matulegulnaz.R
import com.example.matulegulnaz.domain.product.SneakerInfo
import com.example.matulegulnaz.presentation.components.CardItem

@Composable
fun SneakerRow(
    popularList: List<SneakerInfo>,
    onFavorite: (sneakerId: Int) -> Unit,
    onCard: (sneakerId: Int) -> Unit,
    onAddToCart: (sneakerId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ){
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 20.dp)
        ) {
            if(popularList.isNotEmpty()) {
                with(popularList.first()) {
                    CardItem(
                        onFavorite = { onFavorite(this.id) },
                        onAddToCart = { onAddToCart(id) },
                        onCard = { onCard(id) },
                        sneakerInfo = this,
                        cornerImage = R.drawable.ic_increment
                    )
                }

                Spacer(modifier = Modifier.width(19.dp))

                with(popularList[1]) {
                    CardItem(
                        onFavorite = { onFavorite(this.id) },
                        onAddToCart = { onAddToCart(id) },
                        onCard = { onCard(id) },
                        sneakerInfo = this,
                        cornerImage = R.drawable.ic_increment
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
