package com.example.matulegulnaz.presentation.search

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.R
import com.example.matulegulnaz.presentation.common.SneakerViewModel
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.components.CardItem
import com.example.matulegulnaz.presentation.home.CustomHomeTopBar
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading
import com.example.matulegulnaz.presentation.home.search.SearchField
import com.example.matulegulnaz.ui.theme.lightGrey
import com.example.matulegulnaz.ui.theme.lightThemeSurfaceColor
import com.example.matulegulnaz.ui.theme.ralewayFamily

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchScreen(
    onBack: () -> Unit,
    onCancel: () -> Unit,
    onMicro: () -> Unit,
    onCard: (id: Int) -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel(),
    sneakerViewModel: SneakerViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var input by rememberSaveable { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val searchHistoryList = searchViewModel.searchList.collectAsState().value
    val searchedStateFlow = searchViewModel.searchedState.collectAsState().value
    var isSearching by remember { mutableStateOf(false) }

    BackHandler { onBack() }

    CommonScaffold(
        snackbarHostState = snackbarHostState
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(lightThemeSurfaceColor)
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                CustomHomeTopBar(
                    topBarTitle = "Search",
                    titleSize = 20.sp,
                    onLeftActionButton = { onBack() },
                    onUserActionClick = { onCancel() },
                    actionButtonText = "Cancel"
                )

                Spacer(modifier = modifier.height(26.dp))

                SearchField(
                    text = input,
                    onValueChanged = {input = it},
                    placeholder = "Search your shoes",
                    onSearchIcon = {
                        searchViewModel.searchByKeyword(input)
                        searchViewModel.addSearch(input) } ,
                    isSearchScreen = true,
                    onMicro = { onMicro() },
                    keyboardActions = KeyboardActions (onSearch = {
                        searchViewModel.searchByKeyword(input)
                        if(input.isNotEmpty()) searchViewModel.addSearch(input)
                    })
                )

                Spacer(modifier = Modifier.height(16.dp))

                if(!isSearching) LazyColumn {
                    items(searchHistoryList.size) { search ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(bottom = 19.dp)
                                .clickable(onClick = {
                                    input = searchHistoryList[search].text
                                    searchViewModel.searchByKeyword(input)
                                })
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.clock),
                                contentDescription = "clock icon",
                                modifier = Modifier.size(22.dp),
                                tint = lightGrey
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = searchHistoryList[search].text,
                                fontFamily = ralewayFamily,
                                fontWeight = FontWeight.W500,
                                fontSize = 14.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }

                searchedStateFlow.Display(
                    onSuccess = {
                        isSearching = true

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(13.dp),
                            verticalArrangement = Arrangement.spacedBy(13.dp)
                        ) {
                            items(it.size) { sneakerId ->
                                CardItem(
                                    onFavorite = {sneakerViewModel::changeFavoriteState},
                                    onAddToCart = {sneakerViewModel::addToCart},
                                    onCard = {onCard(sneakerId)},
                                    sneakerInfo = it[sneakerId],
                                    cornerImage = R.drawable.ic_increment,
                                )
                            }
                        }
                    },
                    onError = {
                        isSearching = true

                        Box(modifier = Modifier.fillMaxSize()){
                            Column(
                                modifier = Modifier.align(Alignment.Center),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Something went wrong",
                                )
                                Text(
                                    text = "Retry",
                                    modifier = Modifier.clickable(onClick = {
                                        searchViewModel.searchByKeyword(input)
                                    } )
                                )
                            }
                        }
                    },
                    onLoading = {
                        isSearching = true
                        CircleLoading()
                    },
                    onChangeButtonState = {},
                    snackbarHostState = snackbarHostState
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        onBack = {},
        onCancel = {},
        onMicro = {},
        onCard = {}
    )
}