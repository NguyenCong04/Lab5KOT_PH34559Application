package com.congntph34559.fpoly.lab5kot_ph34559application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class ExerciseTwoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetLayoutExerciseTwo()
        }
    }
}

@Composable
fun GetLayoutExerciseTwo() {

    var isChecked by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xff000000)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(
                id = if (isChecked) R.drawable.no_not1
                else R.drawable.not1
            ),
            contentDescription = null,
            modifier = Modifier.size(250.dp, 250.dp)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = {
                isChecked = !isChecked
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xff00FF00),
                checkedTrackColor = Color(0xff008105),
                checkedBorderColor = Color(0xff0DC317),
                uncheckedTrackColor = Color(0xff454545)
            )
        )
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview3() {
    GetLayoutExerciseTwo()
}