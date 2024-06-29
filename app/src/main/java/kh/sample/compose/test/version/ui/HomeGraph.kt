package kh.sample.compose.test.version.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kh.sample.compose.test.version.HomeRoute
import kh.sample.compose.test.version.HomeScreen
import kh.sample.compose.test.version.ProfileRoute
import kh.sample.compose.test.version.R
import kh.sample.compose.test.version.ui.theme.Cyan80
import kh.sample.compose.test.version.ui.theme.KhTypography

@SuppressLint("RestrictedApi")
fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(startDestination = HomeScreen::class, route = HomeRoute::class) {

        composable<HomeScreen> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp)
            ) {
                Row(horizontalArrangement = Arrangement.Center) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Home",
                        style = KhTypography.titleLarge
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_settings_24),
                        contentDescription = "icon_setting",
                        modifier = Modifier.clickable {
                            navController.navigate(ProfileRoute)
                        }
                    )
                }

                Spacer(modifier = Modifier.size(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(Cyan80)
                        .padding(12.dp)

                ) {
                    Text(text = "Title", style = KhTypography.titleMedium)
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document ",
                        style = KhTypography.bodyMedium
                    )

                }

            }
        }
    }
}
