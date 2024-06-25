package kh.sample.compose.test.version

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.toRoute

//dialog<EditProfileScreen>(
//dialogProperties = DialogProperties(
//usePlatformDefaultWidth = false
//)
//) { backStackEntry ->
//    var testSomeValue by rememberSaveable {
//        mutableStateOf("")
//    }
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = Color(0x99000000)),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            modifier = Modifier
//                .height(400.dp)
//                .width(200.dp)
//                .background(color = Color.White)
//                .verticalScroll(rememberScrollState()),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            val data = backStackEntry.toRoute<EditProfileScreen>()
//            Text(text = "Screen B Test\n Test\n Test\n || Name = ${data.name}, Age = ${data.age}")
//
//            TextField(value = testSomeValue, onValueChange = {
//                testSomeValue = it
//
//            }, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done))
//
//
//            Row {
//                Button(onClick = {
//                    navController.previousBackStackEntry?.savedStateHandle?.set(
//                        "submitResult",
//                        testSomeValue
//                    )
//                    navController.popBackStack()
//                }) {
//                    Text(text = "Success")
//                }
//
//                Button(onClick = {
//                    navController.popBackStack()
//                }) {
//                    Text(text = "Fail")
//                }
//            }
//        }
//    }
//}