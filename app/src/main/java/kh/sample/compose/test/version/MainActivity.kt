package kh.sample.compose.test.version

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import kh.sample.compose.test.version.ui.homeGraph
import kh.sample.compose.test.version.ui.theme.Blue40
import kh.sample.compose.test.version.ui.theme.Cyan80
import kh.sample.compose.test.version.ui.theme.KhTypography
import kh.sample.compose.test.version.ui.theme.TestNewVersionComposeTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    companion object {
        const val KEY_UPDATE_PROFILE_DATA = "updateProfile"
        const val KEY_UPDATE_STATUS_DATA = "updateStatus"


        enum class RelationshipStatus(val iconStatus: Int) {
            Single(R.drawable.outline_sentiment_satisfied_24),
            Couple(R.drawable.baseline_sentiment_very_satisfied_24),
            Complicated(R.drawable.outline_sentiment_very_dissatisfied_24)
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestNewVersionComposeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = HomeRoute,
                    modifier = Modifier.statusBarsPadding()
                ) {
                    profileGraph(navController)
                    homeGraph(navController)
                }
            }
        }
    }
}

@Serializable
data object ProfileScreen

@Serializable
data class EditStatus(val status: String, val iconStatus: Int)

@Serializable
data class EditName(val name: String = "")

@Serializable
data object ProfileRoute


@Serializable
data object HomeRoute

@Serializable
data object HomeScreen