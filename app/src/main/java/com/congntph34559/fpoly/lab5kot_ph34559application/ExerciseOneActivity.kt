package com.congntph34559.fpoly.lab5kot_ph34559application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

class ExerciseOneActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetLayoutExerciseOne()
        }
    }
}

@Composable
fun GetLayoutExerciseOne() {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var checkRemember by remember {
        mutableStateOf(false)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    var isShowPass by remember {
        mutableStateOf(false)
    }
    var dialogMessenger by remember {
        mutableStateOf("")
    }

    if (showDialog) {
        DialogCompose(
            onComfirmation = { showDialog = false },
            titleDialog = "Notification",
            messageDialog = dialogMessenger
        )
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xffCCCCCC)),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 5.dp
            )
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                    },
                    label = { Text(text = "Username") }
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    label = { Text(text = "Password") },
                    trailingIcon = {
                        IconButton(onClick = {
                            isShowPass = !isShowPass
                        }) {
                            Icon(
                                painter = painterResource(
                                    id = if (isShowPass) R.drawable.show
                                    else R.drawable.an
                                ),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp, 20.dp)
                            )
                        }

                    },
                    visualTransformation = if (!isShowPass) PasswordVisualTransformation()
                    else VisualTransformation.None

                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 35.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Switch(
                        checked = checkRemember,
                        onCheckedChange = {
                            checkRemember = !checkRemember
                        },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = Color.Black
                        )
                    )
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Remember me ?"
                    )

                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 35.dp, end = 35.dp,
                            top = 10.dp, bottom = 20.dp
                        ),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff242424)
                    ),
                    onClick = {
                        dialogMessenger =
                            if (username.isNotBlank() && password.isNotBlank()) {
                                "Login successfully"
                            } else {
                                "Please enter username and password"
                            }
                        showDialog = true
                    }
                ) {
                    Text(text = "Login")
                }

            }


        }


    }

}

@Composable
fun DialogCompose(
    onComfirmation: () -> Unit,
    titleDialog: String,
    messageDialog: String
) {

    Dialog(onDismissRequest = {}) {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 5.dp
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = titleDialog,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    fontFamily = FontFamily.Serif
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = messageDialog,
                    fontWeight = FontWeight(500),
                    fontFamily = FontFamily.Serif
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onComfirmation,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
                        ),
                        modifier = Modifier.width(100.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Okay")
                    }
                }

            }

        }

    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview2() {
    GetLayoutExerciseOne()
}