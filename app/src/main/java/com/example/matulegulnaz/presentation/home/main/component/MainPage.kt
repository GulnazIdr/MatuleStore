package com.example.matulegulnaz.presentation.home.main.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.R
import com.example.matulegulnaz.presentation.common.SneakerViewModel
import com.example.matulegulnaz.presentation.home.CustomHomeTopBar
import com.example.matulegulnaz.presentation.home.search.MainSearchBar
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun MainPage(
    onCategoryOpen: (id: Int) -> Unit,
    onDrawerOpen: () -> Unit,
    onCartOpen: () -> Unit,
    onSearch: () -> Unit,
    onCard: (id: Int) -> Unit,
    sneakerViewModel: SneakerViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val fetchResultState = sneakerViewModel.fetchResultState.collectAsState().value
    val favoriteResultState = sneakerViewModel.favoriteResultState.collectAsState().value

    val snackbarHostState = remember { SnackbarHostState() }

    favoriteResultState.Display(
        onSuccess = {},
        onError = {},
        onLoading = {},
        onChangeButtonState = {},
        snackbarHostState
    )

    Column(modifier = modifier) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            CustomHomeTopBar(
                topBarTitle = "Explore",
                titleSize = 32.sp,
                onUserActionClick = onCartOpen,
                onLeftActionButton = onDrawerOpen,
                actionButtonIcon = R.drawable.cart_black,
                isMenuScreen = true
            )

            Spacer(modifier = Modifier.height(19.dp))

            MainSearchBar(
                onSettings = {},
                onSearch = { onSearch() }
            )

            fetchResultState.Display(

                onSuccess = {
                    CategoryHeader(
                        onSeeAllClick = {}
                    )

                    CategoryList(
                        onCategory = {onCategoryOpen(it)},
                        listOfButtons = it.categories
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Popular",
                        fontFamily = ralewayFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SneakerRow(
                        popularList = it.sneakers,
                        onFavorite = { id -> sneakerViewModel.changeFavoriteState(id)  },
                        onCard = { onCard(it) },
                        onAddToCart = { sneakerViewModel::addToCart }
                    )
                },

                onLoading = {
                    CircleLoading()
                },

                onError = { },
                onChangeButtonState = {},
                snackbarHostState = snackbarHostState
            )

            AdItem()
        }
    }
}