package com.example.matulegulnaz.presentation.home.main.component

import android.content.res.Resources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matulegulnaz.R
import com.example.matulegulnaz.base.bottom.BottomNavState
import com.example.matulegulnaz.base.bottom.Favorite
import com.example.matulegulnaz.base.bottom.MainPage
import com.example.matulegulnaz.ui.theme.lightGrey

@Composable
fun BottomBar(
    navigateNotif: () -> Unit,
    navigateAccount: (id: Int) -> Unit,
    navController: BottomNavState,
    modifier: Modifier = Modifier
) {
    val path = path(LocalDensity.current)

    Column(
        modifier = modifier
            .fillMaxWidth(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth(1f)
                .offset(0.dp, (-60).dp)
                .drawBehind {
                    drawPath(
                        path = path,
                        color = lightGrey,
                        style = Stroke(width = 3f)
                    )
                }
        )
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            IconButton(onClick = {navController.navigateTo(MainPage) }) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "home icon",
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp),
                    tint = lightGrey,
                )
            }

            Spacer(modifier = Modifier.width(36.dp))

            IconButton(onClick = {navController.navigateTo(Favorite)}) {
                Icon(
                    painter = painterResource(id = R.drawable.heart_empty),
                    contentDescription = "heart icon",
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp),
                    tint = lightGrey
                )
            }
        }

        Row {
            IconButton(onClick = {navigateNotif()}) {
                Icon(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "notification icon",
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp),
                    tint = lightGrey
                )
            }

            Spacer(modifier = Modifier.width(36.dp))

            IconButton(onClick = { navigateAccount(1) }) {
                // TODO: set user id
                Icon(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = "account icon",
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp),
                    tint = lightGrey
                )
            }
        }
    }
}

fun path(density: Density): Path{
    val width = with(density) { Resources.getSystem().configuration.screenWidthDp.dp.toPx()}
    val circle = with(density) {56.dp.toPx()}
    val some = with(density) { 30.dp.toPx() }
    val some2 = with(density){ 10.dp.toPx()}

    val path = Path().apply {
        moveTo(0f, 0f)
        quadraticTo(width/4-circle, some , width/2 - circle/2 - some*2, some)

        moveTo(width/2 - circle/2 - some*2, some)
        quadraticTo(width/2 - circle/2, circle/2 ,width/2 - some - some2, circle)

        moveTo(width/2 - some - some2, circle)
        cubicTo(
            width/2 - circle + 20, circle + some + 20,
            width/2 + circle, circle + some + 20,
            width/2 + circle - 40, circle
        )

        moveTo(width/2 + circle - 40 , circle)
        quadraticTo((width/2 + circle) - some2, some, width/2 + circle + some, some )

        moveTo(width/2 + circle + some, some)
        quadraticTo(width+some/4, some, width, 0f)
    }

    return path
}