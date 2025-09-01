package com.example.matulegulnaz.presentation.home.card

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.snapTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.matulegulnaz.R
import com.example.matulegulnaz.presentation.common.SneakerViewModel
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.components.CustomRoundedButton
import com.example.matulegulnaz.presentation.components.NavigationButton
import com.example.matulegulnaz.presentation.home.CustomHomeTopBar
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading
import com.example.matulegulnaz.ui.theme.darkerGrey
import com.example.matulegulnaz.ui.theme.drawerBackground
import com.example.matulegulnaz.ui.theme.lightGrey
import com.example.matulegulnaz.ui.theme.lightThemeSurfaceColor
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.middleGrey
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.ralewayFamily
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.roundToInt

@Composable
fun CardInfoScreen(
    sneakerId: Int,
    onBackPressed: () -> Unit,
    navigateToCart: () -> Unit,
    sneakerViewModel: SneakerViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    BackHandler { onBackPressed() }

    val snackbarHostState = remember { SnackbarHostState() }
    var detailsExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val fetchResultState = sneakerViewModel.fetchResultState.collectAsState().value
    val addToCartRes = sneakerViewModel.addToCartResult.collectAsState().value

    var currentIndex by remember { mutableIntStateOf(sneakerId) }
    var changeXState by remember { mutableIntStateOf(0) }
    var changeY by remember { mutableStateOf(0) }
    var changeYState by remember { mutableDoubleStateOf(0.0) }
    var sliderModifierRotationState by remember { mutableFloatStateOf(0f) }

    val width = with(LocalDensity.current) {(352.dp - 40.dp).toPx()}
    var swipeableState = remember {
        AnchoredDraggableState(
            initialValue = Anchors.FIRST,
            anchors = DraggableAnchors {
                Anchors.FIRST at 0f
                Anchors.SECOND at 140F
                Anchors.THIRD at 291F
                Anchors.FOURTH at 491F
                Anchors.FIFTH at 601F
                Anchors.SIXTH at 681F
                Anchors.SEVENTH at width
            }
        )
    }

    CommonScaffold(snackbarHostState) { padding ->
        Box(
            modifier = modifier.padding(padding)
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(lightThemeSurfaceColor)
                .padding(horizontal = 20.dp)) {

                Column {
                    CustomHomeTopBar(
                        topBarTitle = "Sneaker shop",
                        titleSize = 16.sp,
                        onLeftActionButton = onBackPressed,
                        onUserActionClick = navigateToCart,
                        actionButtonIcon = R.drawable.cart_black
                    )

                    fetchResultState.Display(
                        onSuccess = {
                            changeXState = swipeableState.offset.roundToInt()
                            changeYState = (changeXState - 410).toDouble().pow(2)
                            changeY = (  changeYState * (-0.0005)).toInt() + 170

                            sliderModifierRotationState = ((changeYState * 0.00009)).toFloat()

                            if (changeXState <= 10 || changeXState >= width.toInt() - 10)
                                sliderModifierRotationState = (changeYState * 0.0002).toFloat()

                            var signChanger = -0.001 * (changeXState - 410)
                            if (signChanger < 0)  sliderModifierRotationState *= -1
                            Log.d("CURRENT INDEX", signChanger.toString())

                            if(it.sneakers.isNotEmpty()) currentIndex = when (changeXState) {
                                in 820..width.toInt() ->
                                    if(it.sneakers.size >= 7) 6 else it.sneakers.size - 1
                                in 681..819 ->
                                    if(it.sneakers.size >= 6) 5 else it.sneakers.size - 1
                                in 601..680 ->
                                    if(it.sneakers.size >= 5) 4 else it.sneakers.size - 1
                                in 491..600 ->
                                    if(it.sneakers.size >= 4) 3 else it.sneakers.size - 1
                                in 291..490 ->
                                    if(it.sneakers.size >= 3) 2 else it.sneakers.size - 1
                                in 140..290 ->
                                    if (it.sneakers.size >= 2) 1 else 0
                                in 0..139 -> 0
                                else -> it.sneakers.size-1
                            }

                            val currentDetails = it.sneakers[currentIndex].details
                            var details =
                                if (currentDetails.isNotEmpty()) {
                                    if (detailsExpanded) currentDetails
                                    else {
                                        if(currentDetails.length >= 121)
                                            currentDetails.substring(0, 121) + "..."
                                        else currentDetails
                                    }
                                } else ""

                            Text(
                                text = it.sneakers[currentIndex].name,
                                fontFamily = ralewayFamily,
                                fontWeight = FontWeight.W700,
                                fontSize = 26.sp,
                                textAlign = TextAlign.Left,
                                color = mainButtonColor,
                                modifier = modifier
                                    .align(Alignment.Start)
                                    .padding(top = 26.dp)
                            )

                            Text(
                                text = it.sneakers[currentIndex].descr,
                                fontFamily = ralewayFamily,
                                fontWeight = FontWeight.W500,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left,
                                color = darkerGrey,
                                modifier = modifier.padding(top = 8.dp)
                            )

                            Text(
                                text = "$${it.sneakers[currentIndex].price}",
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.W600,
                                fontSize = 24.sp,
                                textAlign = TextAlign.Left,
                                color = mainButtonColor,
                                modifier = modifier.padding(top = 8.dp)
                            )


                            AsyncImage(
                                model = it.sneakers[currentIndex].image,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth()
                            )

                            Spacer(modifier = modifier.height(35.dp))

                            Box {
                                Image(
                                    painter = painterResource(id = R.drawable.ellipse_card_info),
                                    contentDescription = "ellipse",
                                    modifier = modifier
                                        .align(Alignment.Center)
                                        .width(352.dp)
                                        .height(77.dp)
                                )

                                Image(
                                    painter = painterResource(id = R.drawable.slider),
                                    contentDescription = "slider dragger",
                                    modifier = modifier
                                        .width(39.dp)
                                        .height(18.dp)
                                        .offset {
                                            IntOffset(
                                                x = changeXState,
                                                y = changeY
                                            )
                                        }
                                        .anchoredDraggable(
                                            swipeableState,
                                            Orientation.Horizontal
                                        )
                                        .rotate(sliderModifierRotationState)
                                )
                            }

                            Spacer(modifier = modifier.height(35.dp))

                            LazyRow {
                                items(it.sneakers.size) { sneaker ->
                                    SneakerRow(
                                        it.sneakers[sneaker].image,
                                        onSneaker = {
                                            currentIndex = sneaker

                                            scope.launch {
                                                swipeableState.snapTo(listOfAnchors[currentIndex])
                                            }
                                        }
                                    )
                                }
                            }

                            Text(
                                text = details,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.W400,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Left,
                                color = lightGrey,
                                lineHeight = 24.sp,
                                modifier = modifier.padding(top = 33.dp)
                            )

                            if(currentDetails.length >= 121)
                                Text(
                                    text = "Read more",
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.W400,
                                    fontSize = 14.sp,
                                    color = drawerBackground,
                                    lineHeight = 21.sp,
                                    modifier = modifier
                                        .clickable(onClick = { detailsExpanded = !detailsExpanded })
                                        .align(Alignment.End)
                                        .padding(top = 9.dp)
                            )
                        },
                        onError = {},
                        onLoading = { CircleLoading() },
                        onChangeButtonState = {},
                        snackbarHostState = snackbarHostState
                    )
                }

                CustomRoundedButton(
                    onAction = {sneakerViewModel::changeFavoriteState},
                    painterResource = R.drawable.heart_empty,
                    backgroundColor = middleGrey,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 28.dp, bottom = 40.dp)
                )

                NavigationButton(
                    text = "Add to cart",
                    icon = R.drawable.cart,
                    onAction = {sneakerViewModel::addToCart},
                    modifier = modifier
                        .padding(bottom = 40.dp)
                        .width(208.dp)
                        .height(50.dp)
                        .align(Alignment.BottomEnd)
                )
            }
        }
    }
}

val listOfAnchors = listOf<Anchors>(
    Anchors.FIRST, Anchors.SECOND, Anchors.THIRD, Anchors.FOURTH, Anchors.FIFTH, Anchors.SIXTH
)

enum class Anchors{
    FIRST,
    SECOND,
    THIRD,
    FOURTH,
    FIFTH,
    SIXTH,
    SEVENTH
}

@Composable
fun SneakerRow(
    image: String,
    onSneaker: () -> Unit,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.width(10.dp))
    Box(
        modifier = modifier
            .size(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable(onClick = {onSneaker()})
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = modifier
                .align(Alignment.Center)
                .rotate(-30f)
        )
    }
}

@Preview
@Composable
private fun CardInfoScreenPreview() {
    CardInfoScreen(
        sneakerId = 1,
        onBackPressed = {},
        navigateToCart = {}
    )
}