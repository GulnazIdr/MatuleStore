package com.example.matulegulnaz.presentation.home.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.R
import com.example.matulegulnaz.presentation.common.SneakerViewModel
import com.example.matulegulnaz.presentation.components.CardItem
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.components.CustomTopAppBar
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading
import io.github.jan.supabase.realtime.Column

@Composable
fun FavoriteScreen(
    sneakerViewModel: SneakerViewModel = hiltViewModel(),
    onCard: (id: Int) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fetchContent = sneakerViewModel.fetchResultState.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }

    CommonScaffold(snackbarHostState) {
        Column(modifier = modifier.padding(it)) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)){
                CustomTopAppBar(
                    onBack = {onBack()},
                    title = "Favorites"
                )

                Spacer(modifier = Modifier.height(24.dp))

                fetchContent.Display(
                    onSuccess = {
                        val favorites = it.sneakers.filter { it.isFavorite }

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(13.dp),
                            verticalArrangement = Arrangement.spacedBy(13.dp)
                        ) {
                            items(favorites.size) {
                                CardItem(
                                    onFavorite = {sneakerViewModel::changeFavoriteState},
                                    onAddToCart = {sneakerViewModel::addToCart},
                                    onCard = {onCard(it)},
                                    sneakerInfo = favorites[it],
                                    cornerImage = R.drawable.ic_increment,
                                )
                            }
                        }
                    },
                    onError = {},
                    onLoading = { CircleLoading() },
                    onChangeButtonState = {},
                    snackbarHostState = snackbarHostState
                )
            }
        }
    }
}

@Preview
@Composable
private fun FavoriteScreenPreview() {
    FavoriteScreen(
        onBack = {},
        onCard = {}
    )
}