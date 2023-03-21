package com.aleksandrov.mfti_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.getSelectedText
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleksandrov.mfti_1.ui.theme.MFTI_1Theme

class VIPClass(val vipValue: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(true) }

            MFTI_1Theme(darkTheme =  isDarkTheme) {
                CompositionLocalProvider(
                    LocalVIPClass provides VIPClass("VIP man")
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        Column {
                            Greeting("Android")
                            CompositionLocalProvider(
                                LocalVIPClass provides VIPClass("BLM Guard")
                            ) {
                                Greeting("Android", modifier= Modifier.clickable
                                { isDarkTheme = !isDarkTheme
                                })
                            }
                        }
                    }

                }
                // A surface container using the 'background' color from the theme
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val vipClass = LocalVIPClass.current
    Text(
        text = "Hello ${vipClass.vipValue}!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun SignInTest() {
    MFTI_1Theme {
        SignIn()
    }
}
@Composable
fun SignIn() {

    LazyColumn(content = {
        item { _title() }
        item { PersonalInfo() }
        item { ButtonEmail() }
    },
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
}

@Composable
fun _title() {
    Column(content = {
        Image(
            modifier = Modifier
                .size(width = 179.dp, height = 139.dp)
                .align(CenterHorizontally),
            painter = painterResource(R.drawable.imagetitle),
            contentDescription = "VegetablesTitle",
            alignment = Alignment.TopCenter
        )
        Text(
            "FoodNinja", modifier =
            Modifier
                .size(width = 150.dp, height = 35.dp)
                .align(CenterHorizontally),
            style = TextStyle(
                fontSize = 27.sp,
                color = Color.Green,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight(600)
            )
        )
        Text(
            "Deliever Favorite Food", modifier = Modifier
                .size(width = 140.dp, height = 16.dp)
                .align(CenterHorizontally),
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Black,
                fontFamily = FontFamily.Default
            )
        )
        Text(
            "Sign Up For Free ", modifier =
            Modifier
                .padding(top = 25.dp, bottom = 10.dp)
                .size(width = 180.dp, height = 24.dp)
                .align(CenterHorizontally),
            style = TextStyle(
                fontSize = 18.sp,
                color = Color.Black,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
            )
        )
    }
    )
}

@Composable
fun PersonalInfo(){
    var text_uname by remember { mutableStateOf(TextFieldValue("")) }
    var text_password by remember { mutableStateOf(TextFieldValue("")) }
    var text_email by remember { mutableStateOf(TextFieldValue("")) }

    var passwordVisible by remember { mutableStateOf(false) }
    Column() {
        TextField(value = text_uname,
            modifier = Modifier
                .padding(8.dp)
                .width(315.dp)
                .shadow(
                    15.dp,
                    shape = RoundedCornerShape(20.dp)
                )
            ,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "personIcon",
                    tint = Color.Green)
            },
            onValueChange = { input ->
                text_uname = input
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(20.dp),
            placeholder = { Text(text = "Username") }
        )
        TextField(value = text_email,
            modifier = Modifier
                .padding(8.dp)
                .width(315.dp)
                .shadow(
                    15.dp,
                    shape = RoundedCornerShape(20.dp)
                ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon",
                    tint = Color.Green)
            },
            onValueChange = { input ->
                text_email = input
            },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = { Text(text = "Email") }
        )
        TextField(value = text_password,
            modifier = Modifier
                .padding(8.dp)
                .width(315.dp)
                .shadow(
                    15.dp,
                    shape = RoundedCornerShape(20.dp)
                ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "lockIcon",
                    tint = Color.Green)
            },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                })
                {
                    Icon(
                        painter = painterResource(R.drawable.twotone_visibility_24),
                        contentDescription = "passwordVisible",
                        tint = Color.LightGray,
                    )
                }
            },
            onValueChange = { input ->
                text_password = input
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = { Text(text = "Password") }
        )


    }
}

@Composable
fun ButtonEmail() {
    var keepSignedIn by remember { mutableStateOf(Color.LightGray) }
    var emailMe by remember { mutableStateOf(Color.LightGray) }
    Column(
        modifier = Modifier
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(modifier= Modifier
            .height(20.dp)
            .width(345.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            IconButton(onClick = { if (keepSignedIn == Color.Green) {
                keepSignedIn = Color.LightGray
            } else {
                keepSignedIn = Color.Green
            }},
            ) {
                Icon(
                    modifier= Modifier
                        .size(18.dp, 18.dp)
                        .clip(CircleShape)
                        .background(keepSignedIn),
                    imageVector = Icons.Default.Done,
                    tint = Color.White,
                    contentDescription = ""
                )
            }
            Text(text="Keep Me Signed In",
                modifier = Modifier.padding(vertical = 2.dp),
                fontSize = 12.sp
            )
        }
        Row(modifier= Modifier
            .padding(vertical = 8.dp)
            .height(20.dp)
            .width(345.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { if (emailMe == Color.Green) {
                emailMe = Color.LightGray
            } else {
                emailMe = Color.Green
            }}) {
                Icon(
                    modifier= Modifier
                        .size(18.dp, 18.dp)
                        .clip(CircleShape)
                        .background(emailMe),
                    imageVector = Icons.Default.Done,
                    tint = Color.White,
                    contentDescription = ""
                )
            }
            Text("Email Me About Special Pricing",
                modifier = Modifier.padding(vertical = 2.dp),
                fontSize = 12.sp
            )
        }
    }

    Button(onClick = {},
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
        modifier = Modifier
            .height(80.dp)
            .padding(top = 30.dp)
    ) {
        Text("Create Account",
            color = Color.White,
        )
    }

    ClickableText(
        modifier = Modifier.padding(top = 13.dp),
        text = AnnotatedString("already have an account?"),
        onClick = { },
        style = TextStyle(
            textDecoration = TextDecoration.Underline,
            color = Color.Green,
            fontSize = 12.sp
        )
    )

}


@Preview(showBackground = true, backgroundColor = 0xfd9245)
@Composable
fun GreetingPreview() {
    MFTI_1Theme {
        Greeting("Android")
    }
}