package kh.sample.compose.test.version

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
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestNewVersionComposeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = ProfileScreen,
                    modifier = Modifier.statusBarsPadding()
                ) {
                    composable<ProfileScreen> { backStackEntry ->
                        var myName = "Saranya"
                        var myStatus = RelationshipStatus.Single
                        val resultUpdatedProfile =
                            backStackEntry.savedStateHandle.getStateFlow(
                                KEY_UPDATE_PROFILE_DATA,
                                ""
                            ).collectAsStateWithLifecycle()

                        val resultUpdateStatus =
                            backStackEntry.savedStateHandle.getStateFlow(
                                KEY_UPDATE_STATUS_DATA,
                                RelationshipStatus.Single.name
                            ).collectAsStateWithLifecycle()

                        if (resultUpdatedProfile.value.isNotEmpty()) {
                            myName = resultUpdatedProfile.value
                        }

                        if (resultUpdateStatus.value.isNotEmpty()) {
                            myStatus = RelationshipStatus.valueOf(resultUpdateStatus.value)
                        }

                        Log.d("print", "result name = ${resultUpdatedProfile.value}")
                        Log.d("print", "result status = ${resultUpdateStatus.value}")

                        Column(
                            modifier = Modifier.padding(horizontal = 18.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Setting",
                                textAlign = TextAlign.Start,
                                style = KhTypography.titleLarge
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Box(
                                contentAlignment = Alignment.Center,
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.mockup_profile),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(120.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            /**
                             * Name
                             */
                            Spacer(modifier = Modifier.size(20.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                    ) {
                                        navController.navigate(
                                            route = EditName(name = myName)
                                        )
                                    }
                                    .padding(bottom = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,

                                ) {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_person_outline_24),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(18.dp),
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                Text(text = "Name", style = KhTypography.labelMedium)
                                Text(
                                    text = myName,
                                    style = KhTypography.bodyMedium,
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.weight(1f)
                                )

                            }
                            Divider()

                            /**
                             * Status
                             */
                            Spacer(modifier = Modifier.size(20.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                    ) {
                                        navController.navigate(
                                            route = EditStatus(
                                                status = myStatus.name,
                                                iconStatus = myStatus.iconStatus
                                            )
                                        )
                                    }
                                    .padding(bottom = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,

                                ) {
                                Image(
                                    painter = painterResource(id = myStatus.iconStatus),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(18.dp),
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                Text(text = "Status", style = KhTypography.labelMedium)
                                Text(
                                    text = myStatus.name,
                                    style = KhTypography.bodyMedium,
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.weight(1f)
                                )

                            }
                            Divider()
                        }
                    }

                    composable<EditName> { backStackEntry ->
                        val data = backStackEntry.toRoute<EditName>()
                        var nameValue by rememberSaveable { mutableStateOf(data.name) }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 12.dp)
                                .safeContentPadding()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                    ) {
                                        navController.popBackStack()
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                                    contentDescription = ""
                                )
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "Change Name",
                                    textAlign = TextAlign.Center,
                                    style = KhTypography.titleSmall
                                )
                                Spacer(modifier = Modifier.size(24.dp))
                            }
                            Spacer(modifier = Modifier.size(20.dp))
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = nameValue,
                                onValueChange = {
                                    nameValue = it
                                },
                                singleLine = true,
                                label = {
                                    Text(text = "Name")
                                }, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Button(onClick = {
                                navController.previousBackStackEntry?.savedStateHandle?.set(
                                    KEY_UPDATE_PROFILE_DATA,
                                    nameValue
                                )
                                navController.popBackStack()
                            }) {
                                Text(modifier = Modifier.padding(horizontal = 12.dp), text = "Save")
                            }
                        }
                    }

                    dialog<EditStatus>(dialogProperties = DialogProperties(usePlatformDefaultWidth = false)) { backStackEntry ->
                        var effectAlreadyLaunched by rememberSaveable { mutableStateOf(false) }
                        var statusSelected by rememberSaveable { mutableStateOf(RelationshipStatus.Single) }
                        LaunchedEffect(key1 = effectAlreadyLaunched) {
                            if (!effectAlreadyLaunched) {
                                val data = backStackEntry.toRoute<EditStatus>()
                                val mapStatusSelected = when (data.status) {
                                    RelationshipStatus.Single.name -> {
                                        RelationshipStatus.Single
                                    }

                                    RelationshipStatus.Couple.name -> {
                                        RelationshipStatus.Couple
                                    }

                                    RelationshipStatus.Complicated.name -> {
                                        RelationshipStatus.Complicated
                                    }

                                    else -> {
                                        RelationshipStatus.Single
                                    }
                                }
                                statusSelected = mapStatusSelected
                                Log.d("print", "LaunchedEffect = $mapStatusSelected")

                            }
                            effectAlreadyLaunched = true
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color(0x99000000)),
                            contentAlignment = Alignment.Center
                        ) {

                            Column(
                                modifier = Modifier
                                    .width(300.dp)
                                    .clip(shape = RoundedCornerShape(12.dp))
                                    .background(color = Color.White)

                            ) {
                                Row(
                                    modifier = Modifier
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null,
                                        ) {
                                            statusSelected = RelationshipStatus.Couple
                                        }
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        modifier = Modifier.size(32.dp),
                                        painter = painterResource(id = R.drawable.baseline_sentiment_very_satisfied_24),
                                        contentDescription = ""
                                    )
                                    Spacer(modifier = Modifier.size(12.dp))
                                    Text(text = "Couple")
                                    Spacer(modifier = Modifier.weight(1f))
                                    if (statusSelected == RelationshipStatus.Couple) {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            painter = painterResource(id = R.drawable.baseline_done_24),
                                            contentDescription = "",
                                            tint = Blue40
                                        )
                                    }

                                }
                                Divider()
                                Row(
                                    modifier = Modifier
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null,
                                        ) {
                                            statusSelected = RelationshipStatus.Single
                                        }
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        modifier = Modifier.size(32.dp),
                                        painter = painterResource(id = R.drawable.outline_sentiment_satisfied_24),
                                        contentDescription = ""
                                    )
                                    Spacer(modifier = Modifier.size(12.dp))
                                    Text(text = "Single")
                                    Spacer(modifier = Modifier.weight(1f))
                                    if (statusSelected == RelationshipStatus.Single) {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            painter = painterResource(id = R.drawable.baseline_done_24),
                                            contentDescription = "",
                                            tint = Blue40
                                        )
                                    }

                                }
                                Divider()
                                Row(
                                    Modifier
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null,
                                        ) {
                                            statusSelected = RelationshipStatus.Complicated
                                        }
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        modifier = Modifier.size(32.dp),
                                        painter = painterResource(id = R.drawable.outline_sentiment_very_dissatisfied_24),
                                        contentDescription = ""
                                    )
                                    Spacer(modifier = Modifier.size(12.dp))
                                    Text(text = "Complicated")
                                    Spacer(modifier = Modifier.weight(1f))
                                    if (statusSelected == RelationshipStatus.Complicated) {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            painter = painterResource(id = R.drawable.baseline_done_24),
                                            contentDescription = "",
                                            tint = Blue40
                                        )
                                    }

                                }

                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Row(
                                        modifier = Modifier
                                            .height(44.dp)
                                            .weight(1f)
                                            .background(color = Blue40)
                                            .clickable {
                                                navController.previousBackStackEntry?.savedStateHandle?.set(
                                                    KEY_UPDATE_STATUS_DATA,
                                                    statusSelected.name
                                                )
                                                navController.popBackStack()
                                            },
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = "Save", color = Color.White)
                                    }
                                    Row(
                                        modifier = Modifier
                                            .height(44.dp)
                                            .weight(1f)
                                            .background(color = Cyan80)
                                            .clickable {
                                                navController.previousBackStackEntry?.savedStateHandle?.set(
                                                    KEY_UPDATE_STATUS_DATA,
                                                    statusSelected.name
                                                )
                                                navController.popBackStack()
                                            },
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = "Cancel")
                                    }

                                }
                            }
                        }

                    }

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