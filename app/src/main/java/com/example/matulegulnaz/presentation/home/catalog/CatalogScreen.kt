package com.example.matulegulnaz.presentation.home.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.R
import com.example.matulegulnaz.presentation.common.SneakerViewModel
import com.example.matulegulnaz.presentation.components.CardItem
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.home.CustomHomeTopBar
import com.example.matulegulnaz.presentation.home.main.component.CategoryHeader
import com.example.matulegulnaz.presentation.home.main.component.CategoryList
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading

@Composable
fun CatalogScreen(
    categoryId: Int,
    onCard: (id: Int) ->  Unit,
    onBack: () -> Unit,
    sneakerViewModel: SneakerViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val fetchContentState = sneakerViewModel.fetchResultState.collectAsState()

    var category_id by remember { mutableIntStateOf(categoryId) }

    CommonScaffold(
        snackbarHostState = snackbarHostState,
        modifier = modifier
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding)
        ){
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {

                fetchContentState.value.Display(
                    onSuccess = {
                        CustomHomeTopBar(
                            topBarTitle = it.categories[category_id].name,
                            onLeftActionButton = { onBack() },
                            onUserActionClick = { sneakerViewModel::changeFavoriteState },
                            actionButtonIcon = R.drawable.heart_empty
                        )

                        CategoryHeader(onSeeAllClick = {})

                        CategoryList(
                            onCategory = { category_id = it },
                            listOfButtons = it.categories
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        val sneakers =
                            if(category_id == 0)
                                it.sneakers
                            else
                                it.sneakers.filter { it.category == category_id }

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(13.dp),
                            verticalArrangement = Arrangement.spacedBy(13.dp)
                        ) {
                            items(sneakers.size) { sneakerId ->
                                CardItem(
                                    onFavorite = {sneakerViewModel::changeFavoriteState},
                                    onAddToCart = {sneakerViewModel::addToCart},
                                    onCard = {onCard(sneakerId)},
                                    sneakerInfo = sneakers[sneakerId],
                                    cornerImage = R.drawable.ic_increment,
                                )
                            }
                        }
                    },
                    onError = {
                        LaunchedEffect(Unit) {
                            snackbarHostState.showSnackbar(
                                message = "Something went wrong. Try again later",
                                withDismissAction = false,
                                duration = SnackbarDuration.Short
                            )
                        }
                    },
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
private fun CatalogScreenPreview() {
    CatalogScreen(
        categoryId =  1,
        onBack = {},
        onCard = {}
    )
}