package com.aleksandrov.mfti_1

import SignIn
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aleksandrov.mfti_1.Catalog.CatalogViewModel
import com.aleksandrov.mfti_1.Catalog.FoodCatalog
import com.aleksandrov.mfti_1.Catalog.detailCatalog.DetailCatalog
import com.aleksandrov.mfti_1.ui.theme.MFTI_1Theme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MFTIApplication: Application()


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }

            val navController = rememberNavController()

            MFTI_1Theme(darkTheme =  isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = "LoginScreen") {
                        composable("LoginScreen") {
                            val signInViewModel = hiltViewModel<SignInView>()
                            SignIn(navController = navController, signInView = signInViewModel)
                        }

                        composable("RestaurantCatalog") {
                            val catalogViewModel = hiltViewModel<CatalogViewModel>()
                            FoodCatalog(navController = navController, catalogViewModel = catalogViewModel)
                        }

                        composable("detail/{name}") {backStackEntry ->
                            DetailCatalog(name = backStackEntry.arguments?.getString("name").orEmpty())
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


/*@Preview(showBackground = true)
@Composable
fun SignInTest() {
    MFTI_1Theme {
        SignIn()
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogTest() {
    MFTI_1Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            FoodCatalog()
        }
    }
}*/
